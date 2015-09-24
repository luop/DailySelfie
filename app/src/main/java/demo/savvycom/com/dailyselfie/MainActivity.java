package demo.savvycom.com.dailyselfie;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import demo.savvycom.com.dailyselfie.model.Image;


public class MainActivity extends ActionBarActivity {
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private File imgFolder;
    private SelfieItemAdapter mSelfieItemAdapter;
    private ArrayList<Image> mArrayListImage;
    private AlarmManager mAlarmManager;

    public static final int REQUEST_NOTIFICATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mArrayListImage = new ArrayList<>();
        imgFolder = new File(Environment.getExternalStorageDirectory(), "MyImages");
        if (!imgFolder.isDirectory()) {
            imgFolder.mkdir();
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mSelfieItemAdapter = new SelfieItemAdapter(mArrayListImage, MainActivity.this);

        /*
        Create new folder to store captured photo
         */


        loadImage();
        mRecyclerView.setAdapter(mSelfieItemAdapter);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2 * 60 * 1000);
                    mAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this.getApplicationContext(), REQUEST_NOTIFICATION, new Intent("com.savvycom.DAILYSELFIE"), Intent.FLAG_ACTIVITY_NEW_TASK);
                    mAlarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME, 2 * 60 * 1000, 2 * 60 * 1000, pendingIntent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void loadImage() {
        new GetImageAsync().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadImage();
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
        if (id == R.id.action_capture) {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date());
            File img = new File(imgFolder, "Selfie_" + timeStamp + ".png");
            Uri savedImg = Uri.fromFile(img);
            i.putExtra(MediaStore.EXTRA_OUTPUT, savedImg);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }


    private class GetImageAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<Image> arrayList = GetImageService.getImageList();
            mArrayListImage.clear();
            mArrayListImage.addAll(arrayList);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mSelfieItemAdapter.notifyDataSetChanged();
        }
    }


}
