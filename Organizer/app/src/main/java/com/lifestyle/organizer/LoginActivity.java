package com.lifestyle.organizer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.securepreferences.SecurePreferences;

import java.util.Map;
import java.util.Set;

/**
 * Created by Chandini on 2/29/2016.
 */
public class LoginActivity extends Activity
{
    public static final String MY_PREFS_NAME = "remember_password";
    private SharedPreferences securePreferences;
    private SharedPreferences sharedPreferences;
    private Animation animScale;
    private EditText userName;
    private EditText password;
    private Button signIn;
    private TextView signUp;
    private Intent intent;
    private CheckBox rememberPassword;

    private String m_userName;
    private String m_password;
    private String m_secureUserName;
    private String m_securePassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        animScale = AnimationUtils.loadAnimation(this,R.anim.anim_scale);
        rememberPassword = (CheckBox) findViewById(R.id.checkBox);
        signIn = (Button) findViewById(R.id.button);
        signUp = (TextView) findViewById(R.id.signup);

        securePreferences = new SecurePreferences(this);
        m_secureUserName = securePreferences.getString("userName","");
        m_securePassword = securePreferences.getString("password","");

        signIn.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {

                m_userName = userName.getText().toString();
                m_password = password.getText().toString();
                if(rememberPassword.isChecked())
                {
                    SharedPreferences pref = getApplicationContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("rememberPassword", true);
                    editor.commit();
                }

                if(m_secureUserName.isEmpty() || m_securePassword.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Dont have an account yet? Sign up!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (m_userName.equals(m_secureUserName) && m_password.equals(m_securePassword))
                    {
                        view.startAnimation(animScale);
                        intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Username and password combination is incorrect, Please try again", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        signUp.setOnClickListener(
                new TextView.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        intent = new Intent(LoginActivity.this, RegisterActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );

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
