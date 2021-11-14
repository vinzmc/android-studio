package id.ac.umn.week11_00000033942;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingActivity extends AppCompatActivity {
    private EditText etServer;
    private Button btnSimpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        etServer = findViewById(R.id.httpServer);
        etServer.setText(MainActivity.server);
        btnSimpan = findViewById(R.id.btnSimpanSetting);
        btnSimpan.setOnClickListener(v -> {
            MainActivity.server = etServer.getText().toString();
            setResult(RESULT_OK);
            finish();
        });
    }
}