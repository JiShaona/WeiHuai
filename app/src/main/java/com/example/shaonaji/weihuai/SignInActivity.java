package com.example.shaonaji.weihuai;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    private static final String TABLE_NAME = "users";
    public static final String USER_NAME = "user_name";
    public static final String USER_PWD = "user_pwd";
    private EditText mName;
    private EditText mAccount;
    private EditText mPwd;
    private Spinner spinner;
    private Button mSignInButton;
    private static int position;
    private static long flag;
    private static SQLiteDatabase db;
    private MySQLiteHelper myHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        //找到注册界面的各个控件
        mName = (EditText) findViewById(R.id.name_et);
        mAccount = (EditText) findViewById(R.id.account_et);
        mPwd = (EditText) findViewById(R.id.password_et);
        spinner = (Spinner) findViewById(R.id.spinner);
        mSignInButton = (Button) findViewById(R.id.sigIn);
        //获取数据库对象
        myHelper = new MySQLiteHelper(this, "users.db", null, 2);
        db = myHelper.getWritableDatabase();
        //为按钮设置点击事件
        mSignInButton.setOnClickListener(mListener);
        spinner.setOnItemSelectedListener(mItemSelectedListener);

    }

    AdapterView.OnItemSelectedListener mItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            position = pos;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };
    View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            signIn();
        }
    };

    public void signIn() {
        if  (isUserNameAndPwdValid()){
            String userName = mName.getText().toString().trim();
            String userAccount= mAccount.getText().toString().trim();
            String userPwd = mPwd.getText().toString().trim();
            String[] location = getResources().getStringArray(R.array.diqu);
            String userLocatioin = location[position];
            //检查用户名、密码是否已存在
            int count = this.findUserByName(userName,userPwd);
            if (count > 0) {
                Toast.makeText(this, getString(R.string.nameOrPwd_already_exist, userName),
                        Toast.LENGTH_SHORT).show();
                return;
            }

            ContentValues values = new ContentValues();
            values.put("user_name", userName);
            values.put("user_account",userAccount);
            values.put("user_pwd", userPwd);
            values.put("user_location", userLocatioin);
            flag = db.insert("users", "id", values);
            if (flag == -1) {
                Toast.makeText(this, getString(R.string.signIn_fail),
                        Toast.LENGTH_SHORT).show();
            } else {
                //成功插入数据，跳转到登录页面
                Toast.makeText(this, getString(R.string.signIn_sucess),
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(SignInActivity.this, LoginActivity.class);
                startActivity(intent);
                this.finish();
            }
        }
//        db.close();
    }

    public boolean isUserNameAndPwdValid() {
        if (mName.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.name_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        }else{
            if (mAccount.getText().toString().trim().equals("")) {
                Toast.makeText(this, getString(R.string.account_empty),
                        Toast.LENGTH_SHORT).show();
                return false;
            } else if (mPwd.getText().toString().trim().equals("")) {
                Toast.makeText(this, getString(R.string.pwd_empty),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }


    public int findUserByName(String userName, String pwd) {
        int result = 0;
        Log.i("database", "findUserByName , userName=" + userName);
        Cursor nameCursor = db.query(TABLE_NAME, null, USER_NAME+"="+userName, null, null, null, null);
        Log.i("database", "findUserByName , userName=" + userName + "and" + "fingUserByPwd,user_pwd=" + pwd);
        Cursor pwdCursor = db.query(TABLE_NAME, null, USER_PWD+"="+pwd, null, null, null, null);
        if (nameCursor != null || pwdCursor != null) {
            result = nameCursor.getCount() + pwdCursor.getCount();

        }
        nameCursor.close();
        pwdCursor.close();
        return result;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
