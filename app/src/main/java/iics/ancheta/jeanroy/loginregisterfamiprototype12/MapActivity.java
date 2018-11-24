package iics.ancheta.jeanroy.loginregisterfamiprototype12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }

    public void ComputeInputPage(View v) {
        Intent newActivity = new Intent(MapActivity.this,InputActivity.class);
        startActivity(newActivity);


    }
}
