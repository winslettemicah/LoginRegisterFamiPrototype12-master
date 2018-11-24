package iics.ancheta.jeanroy.loginregisterfamiprototype12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FundActivity extends AppCompatActivity {
    Button btnBack;
    Button btnSignout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund);

        btnBack = (Button)findViewById(R.id.backbutton);
        btnSignout = (Button)findViewById(R.id.signoutbutton);

        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newActivity = new Intent(FundActivity.this,LoginActivity.class);
                startActivity(newActivity);
            }
        });
        /**btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newActivity = new Intent(FundActivity.this,LoginActivity.class);
                startActivity(newActivity);
            }
        });*/

    }


}
