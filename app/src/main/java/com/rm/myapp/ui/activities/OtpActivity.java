package com.rm.myapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.rm.myapp.R;
import com.rm.myapp.helper.SharedHelper;
import com.rm.myapp.helper.TransparentProgressDialog;
import com.rm.myapp.helper.UserConstant;
import com.rm.myapp.model.DataModel;
import com.rm.myapp.model.LoginModel;
import com.rm.myapp.retrofit.ApiInterface;
import com.rm.myapp.retrofit.AppConfig;

import java.net.SocketTimeoutException;

public class OtpActivity extends AppCompatActivity {

    @BindView(R.id.pinView)
    PinView pinView;
    @BindView(R.id.register_btn)
    Button register_btn;
    String otp;
    TransparentProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        init();
        initUI();
    }
    private void init(){
        ButterKnife.bind(this);
        pd = new TransparentProgressDialog(this, R.drawable.spinner);
        otp = getIntent().getStringExtra("otp");
        System.out.println("++++++ "+otp);
    }

    private void initUI(){
        pinView.setText(otp);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = pinView.getText().toString();
                if (TextUtils.isEmpty(otp)){
                    Toast.makeText(OtpActivity.this, "Enter OTP!", Toast.LENGTH_SHORT).show();
                }else if(otp.length()<4){
                    Toast.makeText(OtpActivity.this, "Enter valid OTP!", Toast.LENGTH_SHORT).show();
                }else {
                    verifyOTP(otp);
                }
            }
        });
    }


    private void verifyOTP(String otp){
        pd.show();
        ApiInterface apiInterface1 = AppConfig.getRetrofit().create(ApiInterface.class);
        Call<LoginModel> call = apiInterface1.verifyOTP(AppConfig.Key,otp);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                pd.dismiss();
                if (response.isSuccessful()){
                    String token = response.body().getToken();
                    SharedHelper.putBooleanKey(OtpActivity.this, UserConstant.isLogin,true);
                    SharedHelper.putKey(OtpActivity.this,UserConstant.token,token);
                    startActivity(new Intent(OtpActivity.this,MainActivity.class));
                    finish();


                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                pd.dismiss();
                if (t instanceof SocketTimeoutException) {
                    verifyOTP(otp);
                }
            }
        });
    }

}