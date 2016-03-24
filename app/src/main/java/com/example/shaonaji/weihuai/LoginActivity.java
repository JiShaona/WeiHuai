package com.example.shaonaji.weihuai;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private static final String TABLE_NAME = "users";
    public static final String ID = "_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_PWD = "user_pwd";
    public static final String USER_LOCATION = "user_location";

    private EditText mName;
    private EditText mPwd;
    private Button mLoginButton;
    public static SQLiteDatabase db;
    private MySQLiteHelper mySQLiteHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mName= (EditText) findViewById(R.id.name);
        mPwd= (EditText) findViewById(R.id.password);
        mLoginButton= (Button) findViewById(R.id.button);
        //获取数据库对象
        mySQLiteHelper=new MySQLiteHelper(this, "users.db", null, 2);
        db=mySQLiteHelper.getWritableDatabase();

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }
    public void login() {
        if (isUserNameAndPwdValid()) {

            String userName = mName.getText().toString().trim();
            String userPwd = mPwd.getText().toString().trim();
            int result=this.findUserByNameAndPwd(userName, userPwd);
            Log.i("result:", String.valueOf(result));
            if(result==0){
                Toast.makeText(this, getString(R.string.logIn_fail),
                        Toast.LENGTH_SHORT).show();
                return;
            }else {
                Toast.makeText(this, getString(R.string.user_logIn_sucess),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
                this.finish();
            }


        }

    }
    public boolean isUserNameAndPwdValid() {
        if (mName.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.name_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPwd.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public int findUserByNameAndPwd(String userName,String pwd){
        int result=0;
        Cursor mCursor=db.query(TABLE_NAME, null, USER_NAME+"="+userName+" and "+USER_PWD+"="+pwd,
                null, null, null, null);
        if(mCursor!=null){
            result=mCursor.getCount();
            mCursor.close();
            Log.i("database", "findUserByNameAndPwd , result=" + result);
        }
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_in, menu);
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


