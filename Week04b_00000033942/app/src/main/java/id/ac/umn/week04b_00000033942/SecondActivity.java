package id.ac.umn.week04b_00000033942;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    Button btnKirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

//        Intent mainIntent = getIntent();
//        String pesanD = mainIntent.getStringExtra("PesanDariMain");
//        output2.setText(pesanD);
    }
}