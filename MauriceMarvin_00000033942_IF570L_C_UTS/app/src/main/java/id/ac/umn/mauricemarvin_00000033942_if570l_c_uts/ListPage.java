package id.ac.umn.mauricemarvin_00000033942_if570l_c_uts;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.ac.umn.mauricemarvin_00000033942_if570l_c_uts.model.Sfx;
import id.ac.umn.mauricemarvin_00000033942_if570l_c_uts.model.SfxAdapter;

public class ListPage extends AppCompatActivity {
    private final ArrayList<Sfx> sfxsArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_page);

        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (intent != null && actionBar != null) {
            actionBar.setTitle(extras.getString("USERNAME"));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //inisiasi konten
        sfxsArray.add(new Sfx(R.raw.ear_rape, "Ear Rape", "Meme"));
        sfxsArray.add(new Sfx(R.raw.spongebob, "Spongebob Kecoa", "Meme"));
        sfxsArray.add(new Sfx(R.raw.may, "Lumba lumba May", "Meme"));
        sfxsArray.add(new Sfx(R.raw.mgs_alert, "MGS Alert", "Game SFX"));
        sfxsArray.add(new Sfx(R.raw.bruh, "Bruh!", "Meme"));

        // Lookup the recyclerview in activity layout
        RecyclerView mRecycleView = findViewById(R.id.sfxRecyclerView);
        SfxAdapter mAdapter = new SfxAdapter(this, sfxsArray);

        // Attach the adapter to the recyclerview to populate items
        mRecycleView.setAdapter(mAdapter);
        // Set layout manager to position the items
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
    }
}