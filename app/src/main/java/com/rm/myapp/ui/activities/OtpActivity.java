package com.rm.myapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.rm.myapp.R;

public class OtpActivity extends AppCompatActivity {

    @BindView(R.id.pinView)
    PinView pinView;
    @BindView(R.id.register_btn)
    Button register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        init();
        initUI();
    }
    private void init(){
        ButterKnife.bind(this);
    }

    private void initUI(){
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = pinView.getText().toString();
                if (TextUtils.isEmpty(otp)){
                    Toast.makeText(OtpActivity.this, "Enter OTP!", Toast.LENGTH_SHORT).show();
                }else if(otp.length()<4){
                    Toast.makeText(OtpActivity.this, "Enter valid OTP!", Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(OtpActivity.this,ProfileActivity.class));
                    finish();
                }
            }
        });
    }
}