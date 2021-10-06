package id.ac.umn.week02_00000033942;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText angka1, angka2;
    TextView hasil;
    Button btnTambah, btnKurang, btnKali, btnBagi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        angka1 = (EditText) this.findViewById(R.id.angka1);
        angka2 = (EditText) this.findViewById(R.id.angka2);
        hasil = (TextView) this.findViewById(R.id.hasil);
        btnTambah = (Button) this.findViewById(R.id.btnTambah);
        btnKurang = (Button) this.findViewById(R.id.btnKurang);
        btnKali = (Button) this.findViewById(R.id.btnKali);
        btnBagi = (Button) this.findViewById(R.id.btnBagi);

//        --tipe 1
//        btnBagi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                hitung('/');
//            }
//        });
//
//        --tipe 2
//        btnTambah.setOnClickListener(v -> hitung('+'));
//
//        tipe 1 dan 2 sama aja, tipe 2 lebih singkat
//
        btnTambah.setOnClickListener(v -> hitung('+'));
        btnKurang.setOnClickListener(v -> hitung('-'));
        btnKali.setOnClickListener(v -> hitung('*'));
        btnBagi.setOnClickListener(v -> hitung('/'));
    }

    protected void hitung(char operator) {
        if (angka1.getText().toString().isEmpty() || angka2.getText().toString().isEmpty()) {
            return;
        }
        double operand1 = Double.parseDouble(angka1.getText().toString());
        double operand2 = Double.parseDouble(angka2.getText().toString());
        double result = 0.0;
        switch (operator) {
            case ('+'):
                result = operand1 + operand2;
                break;
            case ('-'):
                result = operand1 - operand2;
                break;
            case ('*'):
                result = operand1 * operand2;
                break;
            case ('/'):
                result = operand1 / operand2;
                break;
        }
        hasil.setText(String.valueOf(result));
    }
}