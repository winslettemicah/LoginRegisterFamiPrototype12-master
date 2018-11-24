package iics.ancheta.jeanroy.loginregisterfamiprototype12;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainMenuActivity extends AppCompatActivity {
    //Button btnProceed;
    Button btnSignout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Bundle bn = getIntent().getExtras();
        String Username = bn.getString("TransferredUsername");
        final TextView UsernameText = (TextView) findViewById(R.id.tvUsername);
        UsernameText .setText(String.valueOf(Username));
        //btnProceed = (Button)findViewById(R.id.proceedButton);
        btnSignout = (Button)findViewById(R.id.signoutButton);
        LoginPage();
    }


    public void LoginPage(){
        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newActivity = new Intent(MainMenuActivity.this,LoginActivity.class);
                startActivity(newActivity);


            }
        });
    }

    public void ComputeInputPage(View v) {
        Intent newActivity = new Intent(MainMenuActivity.this,InputActivity.class);
        startActivity(newActivity);


    }

    public void MapPage(View v) {
        Intent newActivity = new Intent(MainMenuActivity.this,MapActivity.class);
        startActivity(newActivity);


    }
    public void DisplayFundTable(View v) {
        Intent newActivity = new Intent(MainMenuActivity.this,FundActivity.class);
        startActivity(newActivity);

    }
}
