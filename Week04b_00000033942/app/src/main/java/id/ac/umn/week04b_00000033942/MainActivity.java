package id.ac.umn.week04b_00000033942;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnHalaman1, btnHalaman2;
    TextView nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnHalaman1 = (Button) this.findViewById(R.id.btnHalaman1);
        btnHalaman2 = (Button) this.findViewById(R.id.btnHalaman2);
        nama = (TextView) this.findViewById(R.id.nama);

        btnHalaman1.setOnClickListener(v -> {
            Intent intentDua = new Intent(MainActivity.this, SecondActivity.class);
            startActivityIfNeeded(intentDua, 1);
        });

        btnHalaman2.setOnClickListener(v -> {
            Intent intentKetiga = new Intent(MainActivity.this, ThirdActivity.class);
            startActivityIfNeeded(intentKetiga, 1);
        });
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//    }
}