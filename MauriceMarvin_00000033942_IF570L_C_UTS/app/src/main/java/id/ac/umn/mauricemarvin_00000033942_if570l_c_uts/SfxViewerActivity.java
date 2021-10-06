package id.ac.umn.mauricemarvin_00000033942_if570l_c_uts;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class SfxViewerActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Button playBtn;
    TextView title, subTitle;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfx_viewer);

        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getIntent().getExtras().getString("NAME"));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        playBtn = findViewById(R.id.playBtn);
        title = findViewById(R.id.sfxTitle);
        subTitle = findViewById(R.id.sfxSubTitle);
        id = getIntent().getExtras().getInt("ID");
        title.setText(getIntent().getExtras().getString("NAME"));
        subTitle.setText(getIntent().getExtras().getString("CATEGORY"));
        playBtn.setOnClickListener(v -> playBtnAction());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

    public void playBtnAction() {
        mediaPlayer = MediaPlayer.create(this, id);
        mediaPlayer.setOnCompletionListener(mp -> {
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
            mediaPlayer = null;
        });
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
        mediaPlayer.start();
    }
}