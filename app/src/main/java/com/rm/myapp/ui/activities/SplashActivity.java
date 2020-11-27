package com.rm.myapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.rm.myapp.R;
import com.rm.myapp.helper.SharedHelper;
import com.rm.myapp.helper.UserConstant;


public class SplashActivity extends AppCompatActivity {
    private static final String TAG ="SplashActivity" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
       // getToken();
        init();
    }

    private void init(){

       boolean token= SharedHelper.getBooleanKey(SplashActivity.this, UserConstant.isLogin,false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                if (token){

                    startActivity(new Intent(SplashActivity.this, MainActivity.class));

                }else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                finish();

            }
        }, 2000);
    }

   /* private void getToken(){
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {


                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        SharedHelper.putKey(SplashActivity.this, UserConstant.firbasetoken, token);
                        // Log and toast

                        Log.d(TAG, token);
                        // Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
    }*/
}