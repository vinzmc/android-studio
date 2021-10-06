package id.ac.umn.mauricemarvin_00000033942_if570l_c_uts;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button buttonProfile, buttonLibrary;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        buttonProfile = findViewById(R.id.buttonProfile);
        buttonLibrary = findViewById(R.id.buttonLibrary);

        Bundle extras = getIntent().getExtras();
        if (username == null) {
            if (extras != null) {
                username = extras.getString("USERNAME");
                //redirect setelah username tersimpan
                if(extras.getString("PAGE").equals("Profile")){
                    profilePage();
                }else {
                    libraryPage();
                }
            }
        }

        buttonProfile.setOnClickListener(v -> profilePage());
        buttonLibrary.setOnClickListener(v -> libraryPage());
    }

    public void openLoginPage(String page) {
        Intent intent = new Intent(this, LoginPage.class);
        intent.putExtra("PAGE", page);
        startActivity(intent);
    }

    public void profilePage() {
        if (username == null) {
            openLoginPage("Profile");
        } else {
            Intent intent = new Intent(this, ProfilePage.class);
            startActivity(intent);
        }
    }

    public void libraryPage() {
        if (username == null) {
            openLoginPage("Library");
        } else {
            Intent intent = new Intent(this, ListPage.class);
            intent.putExtra("USERNAME", username);
            startActivity(intent);
        }
    }
}