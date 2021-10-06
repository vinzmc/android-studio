package id.ac.umn.week04a_00000033942;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ActivityDua extends AppCompatActivity {
    private TextView pesanDiterima;
    private EditText pesanBalik;
    private Button btnKirimBalik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dua);

        pesanDiterima = findViewById(R.id.pesanDiterima);
        pesanBalik = findViewById(R.id.pesanBalik);
        btnKirimBalik = findViewById(R.id.btnKirimBalik);

        Intent mainIntent = getIntent();
        String pesanD = mainIntent.getStringExtra("PesanDariMain");
        pesanDiterima.setText(pesanD);
    }

    public void kirimBalik(View view) {
        String jawaban = pesanBalik.getText().toString();
        Intent balasIntent = new Intent();
        balasIntent.putExtra("Jawaban", jawaban);
        setResult(RESULT_OK, balasIntent);
        finish();
    }
}