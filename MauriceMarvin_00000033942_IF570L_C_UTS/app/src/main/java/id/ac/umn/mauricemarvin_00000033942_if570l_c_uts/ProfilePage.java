package id.ac.umn.mauricemarvin_00000033942_if570l_c_uts;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ProfilePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Profile");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}