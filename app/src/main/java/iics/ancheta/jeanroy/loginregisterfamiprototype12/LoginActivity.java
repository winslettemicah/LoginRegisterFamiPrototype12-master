package iics.ancheta.jeanroy.loginregisterfamiprototype12;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * working Login and Register???
 */
public class LoginActivity extends AppCompatActivity {

    //Declaration EditTexts
    EditText editTextEmail;
    EditText editTextPassword;

    //Declaration TextInputLayout
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    //Declaration Button
    Button buttonLogin;
    Button buttonRememberMe;

    //Declaration TextView
    TextView saveText;

    //Declaration SqliteHelper
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sqliteHelper = new SqliteHelper(this);
        initCreateAccountTextView();
        initViews();

        //set click event of login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check user input is correct or not
                if (validate()) {

                    //Get values from EditText fields
                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();

                    //Authenticate user
                    User currentUser = sqliteHelper.Authenticate(new User(null, null, Email, Password));

                    //Check Authentication is successful or not
                    if (currentUser != null) {
                        //Snackbar.make(buttonLogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();
                        Cursor res = sqliteHelper.getUsername(Email);

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("" + res.getString(0));
                        }
                        String StoredUsername = buffer.toString();


                        //User Logged in Successfully Launch You home screen activity
                        Intent intent = new Intent(LoginActivity.this,MainMenuActivity.class);
                        intent.putExtra("TransferredUsername",  StoredUsername);
                        startActivity(intent);
                        //finish();
                    } else {

                        //User Logged in Failed
                        Snackbar.make(buttonLogin, "Failed to log in , please try again", Snackbar.LENGTH_LONG).show();

                    }
                }
            }
        });


    }

    //this method used to set Create account TextView text and click event( maltipal colors
    // for TextView yet not supported in Xml so i have done it programmatically)
    private void initCreateAccountTextView() {
        TextView textViewCreateAccount = (TextView) findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setText(fromHtml("<font color='#ffffff'>I don't have account yet. </font><font color='#0c0099'>create one</font>"));
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    //this method is used to connect XML views to its Objects
    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

    }

    //This method is for handling fromHtml method deprecation
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    //This method is used to validate input given by user
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            textInputLayoutEmail.setError("Please enter valid email!");
        } else {
            valid = true;
            textInputLayoutEmail.setError(null);
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password is to short!");
            }
        }

        return valid;
    }

    /**public void ViewAll(){
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = sqliteHelper.getAllData();
                if(res.getCount() == 0){
                    //show message
                    showMessage("Error", "Nothing Found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Id : " + res.getString(0) + "\n");
                    //buffer.append("Firstname : " + res.getString(1) + "\n");
                    //buffer.append("Lastname : " + res.getString(photo) + "\n");
                    buffer.append("Email : " + res.getString(2) + "\n");
                    buffer.append("Password : " + res.getString(3) + "\n\n");
                }

                //Show all data
                showMessage("Data", buffer.toString());
            }
        });
    }
     */
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    //Save the users login info
    public void saveInfo(View view){
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();


        editor.putString("username", editTextEmail.getText().toString());
        editor.putString("password", editTextPassword.getText().toString());
        editor.apply();

        Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show();
    }

    //Priint out the saved data
    /**public void displayData(View view){
        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        String name = sharedPref.getString("username", "");
        String pw = sharedPref.getString("password", "");

        buttonRememberMe = (Button) findViewById(R.id.buttonRememberMe);
        saveText = (TextView) findViewById(R.id.savedText);

        saveText.setText(name + " " + pw);
    }
     **/

}
