package com.example.shaonaji.weihuai;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    private Button xueTang;
    private Button dongMan;
    private Button feiZaoJu;
    private Button wo;
    private Button linShi;
    private Button dianYou;
    private Button liYu;
    private String tag="MenuActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findView();
        setListener();

    }

    public void findView(){
        xueTang= (Button) findViewById(R.id.xuetang);
        dongMan= (Button) findViewById(R.id.dongman);
        feiZaoJu= (Button) findViewById(R.id.feizaoju);
        wo= (Button) findViewById(R.id.wo);
        linShi= (Button) findViewById(R.id.lingshi);
        dianYou= (Button) findViewById(R.id.dianyou);
        liYu= (Button) findViewById(R.id.liyu);

    }
    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.xuetang:
                    xueTang();
                    break;
                case R.id.dongman:
                    dongMan();;
                    break;
                case R.id.feizaoju:
                    feiZaoJu();
                    break;
                case R.id.wo:
                    wo();
                    break;
                case R.id.lingshi:
                    lingShi();
                    break;
                case R.id.dianyou:
                    dianYou();
                    break;
                case R.id.liyu:
                    liYu();
                    break;
            }
        }
    };
    public void setListener(){
        xueTang.setOnClickListener(mListener);
        dongMan.setOnClickListener(mListener);
        feiZaoJu.setOnClickListener(mListener);
        wo.setOnClickListener(mListener);
        linShi.setOnClickListener(mListener);
        dianYou.setOnClickListener(mListener);
        liYu.setOnClickListener(mListener);
    }
    public void xueTang(){
        Intent intent = new Intent();
        intent.setClass(MenuActivity.this, XueTang.class);
        this.startActivity(intent);
    }
    public void dongMan(){
        Intent intent = new Intent();
        intent.setClass(MenuActivity.this,DongMan.class);
        this.startActivity(intent);
    }
    public void feiZaoJu(){
        Intent intent = new Intent();
        intent.setClass(MenuActivity.this,FeiZaoJu.class);
        this.startActivity(intent);
    }
    public void wo(){
        Intent intent = new Intent();
        intent.setClass(MenuActivity.this,Wo.class);
        this.startActivity(intent);
    }
    public void lingShi(){
        Intent intent = new Intent();
        intent.setClass(MenuActivity.this,LingShi.class);
        this.startActivity(intent);
    }
    public void dianYou(){
        Intent intent = new Intent();
        intent.setClass(MenuActivity.this,DianYou.class);
        this.startActivity(intent);
    }
    public void liYu(){
        Intent intent = new Intent();
        intent.setClass(MenuActivity.this,LiYu.class);
        this.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
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
