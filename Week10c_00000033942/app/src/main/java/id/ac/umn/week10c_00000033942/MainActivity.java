package id.ac.umn.week10c_00000033942;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Void> {
    private TextView mTextView;
    private ProgressBar mProgressBar;
    CustomBoundService customBoundService;
    boolean isBound = false;

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            CustomBoundService.CustomLocalBinder binder = (CustomBoundService.CustomLocalBinder) service;
            customBoundService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView1);
        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setMax(100);
        Button btnStartService = findViewById(R.id.main_button_startservice);
        Button btnShowTime = findViewById(R.id.main_button_showtime);

        btnShowTime.setOnClickListener(v -> {
            String currentTime = customBoundService.getCurrentTime();
            Toast.makeText(getApplicationContext(), currentTime, Toast.LENGTH_LONG).show();
        });

        btnStartService.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CustomService.class);
            startService(intent);
        });

        Intent servIntent = new Intent(this, SimpleIntentService.class);
        startService(servIntent);

        Intent intent2 = new Intent(this, CustomBoundService.class);
        bindService(intent2, serviceConnection, Context.BIND_AUTO_CREATE);
    }


    public void startTask(View view) {
        if (!getSupportLoaderManager().hasRunningLoaders())
            getSupportLoaderManager().initLoader(0, null, this);
    }

    @NonNull
    @Override
    public Loader<Void> onCreateLoader(int id, @Nullable Bundle args) {
        AsyncTaskLoader<Void> asyncTaskLoader = new ContohLoader(this, (int) (Math.random() * 50) + 10, this);
        asyncTaskLoader.forceLoad();
        return asyncTaskLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Void> loader, Void data) {
        getSupportLoaderManager().destroyLoader(0);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Void> loader) {
    }

    static class ContohLoader extends AsyncTaskLoader<Void> {
        static WeakReference<MainActivity> mActivity;
        int mCounter = 0;

        public ContohLoader(@NonNull Context context, int n, MainActivity main) {
            super(context);
            mCounter = n;
            mActivity = new WeakReference<MainActivity>(main);
        }

        @Nullable
        @Override
        public Void loadInBackground() {
            try {
                for (int i = 0; i <= mCounter; i++) {
                    Thread.sleep(200);
                    final int progress = ((int) ((100 * i) / (float) mCounter));
                    if (mActivity.get() != null) {
                        mActivity.get().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (progress < 100) {
                                    mActivity.get().mProgressBar.setProgress(progress);
                                    mActivity.get().mTextView.setText("progress = " + progress + " persen");
                                } else {
                                    mActivity.get().mProgressBar.setProgress(100);
                                    mActivity.get().mTextView.setText("Selesai selama " + mCounter * 200 + "milidetik...");
                                }
                            }
                        });
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}