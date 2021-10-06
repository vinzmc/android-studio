package id.ac.umn.week03_00000033942;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.LinkedList;
import java.util.Objects;

import id.ac.umn.week03_00000033942.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private final LinkedList<String> mDaftarKata = new LinkedList<>();
    private RecyclerView mRecycleView;
    private DaftarKataAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int jumlahKata = mDaftarKata.size();
                mDaftarKata.addLast("Kata " + (jumlahKata + 1) + " telah ditambahkan");
                Objects.requireNonNull(mRecycleView.getAdapter()).notifyItemInserted(jumlahKata);
                mRecycleView.smoothScrollToPosition(jumlahKata);
            }
        });

        for (int i = 1; i < 21; i++){
            mDaftarKata.add("Kata " + i);
        }

        mRecycleView = (RecyclerView) findViewById(R.id.recyclerview);
        mAdapter = new DaftarKataAdapter(this, mDaftarKata);
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}