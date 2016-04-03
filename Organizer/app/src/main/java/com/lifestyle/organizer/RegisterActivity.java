package com.lifestyle.organizer;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.securepreferences.SecurePreferences;

/**
 * Created by Chandini on 2/29/2016.
 */
public class RegisterActivity extends Activity
{
    private Animation animScale;
    private EditText userName;
    private EditText password;
    private EditText confirmPassword;
    private Button register;
    private SharedPreferences securePreferences;
    private Intent intent;

    private String m_userName;
    private String m_password;
    private String m_confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        confirmPassword = (EditText) findViewById(R.id.confirm_password);
        animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        register = (Button) findViewById(R.id.button);

        securePreferences = new SecurePreferences(this);

        register.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                m_userName = userName.getText().toString();
                m_password = password.getText().toString();
                m_confirmPassword = confirmPassword.getText().toString();

                if(m_userName.isEmpty() || m_password.isEmpty() || m_confirmPassword.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this,"Please enter all the fields",Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (m_password.equals(m_confirmPassword))
                    {
                        securePreferences.edit().putString("userName", m_userName).commit();
                        securePreferences.edit().putString("password", m_password).commit();

                        view.startAnimation(animScale);
                        intent = new Intent(RegisterActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        password.setText("");
                        confirmPassword.setText("");
                        Toast.makeText(RegisterActivity.this,"Passwords don't match, please reenter", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

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

