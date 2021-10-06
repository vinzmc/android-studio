package id.ac.umn.mauricemarvin_00000033942_if570l_c_uts;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class LoginPage extends AppCompatActivity {
    TextInputLayout til;
    EditText inputLogin;
    Button login;
    Toast toastAndroid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        til = findViewById(R.id.text_input_layout);
        inputLogin = findViewById(R.id.inputLogin);
        login = findViewById(R.id.buttonLogin);

        login.setOnClickListener(v -> loginButtonOnclick());

        inputLogin.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login.performClick();
                return true;
            }
            return false;
        });
    }

    public void loginButtonOnclick() {
        if (inputLogin.getText().length() == 0) {
            til.setError("Input nama tidak boleh kosong");
        } else {
            Bundle extras = getIntent().getExtras();
            String username = inputLogin.getText().toString();
            toastAndroid = Toast.makeText(LoginPage.this, "Selamat datang, " + username, Toast.LENGTH_SHORT);
            toastAndroid.show();

            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            intent.putExtra("USERNAME", username);
            intent.putExtra("PAGE", extras.getString("PAGE"));
            startActivity(intent);
            finish();
        }

    }
}