package id.ac.umn.week04a_00000033942;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText inputText, inputUrl;
    private TextView tvJawaban;
    private Button btnBrowse, btnKirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = (EditText) this.findViewById(R.id.inputText);
        inputUrl = (EditText) this.findViewById(R.id.inputUrl);
        btnBrowse = (Button) this.findViewById(R.id.btnBrowse);
        btnKirim = (Button) this.findViewById(R.id.btnKirim);
        tvJawaban = (TextView) this.findViewById(R.id.jawaban);

        btnBrowse.setOnClickListener(v -> {
            String urlText = inputUrl.getText().toString();
            if (urlText.isEmpty()) {
                urlText = "https://umn.ac.id/";
            }
            Intent browseIntent = new Intent(Intent.ACTION_VIEW);
            browseIntent.setData(Uri.parse(urlText));

            if (browseIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(browseIntent);
            }
//            Better way
//            try {
//                startActivity(browseIntent);
//            } catch(Exception e) {
//                Log.d("Package Manager Error",e.toString());
//            }
        });

        btnKirim.setOnClickListener(v -> {
            Intent intentDua = new Intent(MainActivity.this, ActivityDua.class);
            String isian = inputText.getText().toString();
            intentDua.putExtra("PesanDariMain", isian);
            startActivityIfNeeded(intentDua, 1);
        });
    }//akhir dari oncreate

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String jawaban = data.getStringExtra("Jawaban");
                tvJawaban.setText(jawaban);
            }
        }
    }
}