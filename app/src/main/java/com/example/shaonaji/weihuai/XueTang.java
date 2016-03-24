package com.example.shaonaji.weihuai;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

public class XueTang extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private PopupWindow pop;
    private PageTurnView pageTurnView;

    FrameLayout frameLayout;
    private void initPopMenu(){
        if (pop == null){
            pop = new PopupWindow(getLayoutInflater().inflate(R.layout.pop,null),
                    ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.FILL_PARENT);
            pop.setOutsideTouchable(true);
        }
        if (pop.isShowing()){
            pop.dismiss();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xue_tang);
        pageTurnView = (PageTurnView) findViewById(R.id.main_pcv);
        Bitmap bitmap = null;
        List<Bitmap> bitmaps = new ArrayList<Bitmap>();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.page_img_a);
        bitmaps.add(bitmap);
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.page_img_b);
        bitmaps.add(bitmap);
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.page_img_c);
        bitmaps.add(bitmap);
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.page_img_d);
        bitmaps.add(bitmap);
        pageTurnView.setBitmaps(bitmaps);

        frameLayout = (FrameLayout) findViewById(R.id.fl_root);

        mediaPlayer=MediaPlayer.create(this,R.raw.a);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

    }

    public void btnClick(View v) {
        initPopMenu();
        pop.showAsDropDown(v);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_xue_tang, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){

            case R.id.stop: {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
            break;
            case R.id.start:{
                mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.a);
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
            }
            break;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}
