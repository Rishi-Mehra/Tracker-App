package com.rm.myapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rm.myapp.R;
import com.rm.myapp.helper.TransparentProgressDialog;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

   private Unbinder unbinder;
    @BindView(R.id.mobile_etxt)
    EditText mobile_etxt;
    @BindView(R.id.register_btn)
    Button register_btn;

    String name, email;
    String idToken, id;
    TransparentProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        initUI();


    }

    private void init() {
        unbinder=  ButterKnife.bind(this);
       // pd = new TransparentProgressDialog(this, R.drawable.spinner);

    }

    private void initUI() {
        register_btn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_btn:
                String mobileNumber = mobile_etxt.getText().toString();
                if (mobileNumber.length()<10){
                    Toast.makeText(this, "Please enter valid Mobile Number!", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(mobileNumber)){
                    Toast.makeText(this, "Please enter  Mobile Number!", Toast.LENGTH_SHORT).show();
                }else {
                   // userLogin(mobileNumber);
                    startActivity(new Intent(this,OtpActivity.class));
                    finish();
                }
                break;

        }
    }

   /* private void userLogin(String mobileNumber){
        ApiInterface apiInterface1 = AppConfig.getRetrofit().create(ApiInterface.class);
        Call<LoginModel> call = apiInterface1.loginUser(email,"123456");
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                pd.dismiss();
                if (response.isSuccessful()){

                    if (response.body().getSuccess()){
                        SharedHelper.putBooleanKey(LoginActivity.this, UserConstant.isLogin, true );
                        SharedHelper.putKey(LoginActivity.this, UserConstant.id, String.valueOf(response.body().getUserDetail().getId()));
                        SharedHelper.putKey(LoginActivity.this, UserConstant.name,  response.body().getUserDetail().getName().toString());
                        SharedHelper.putKey(LoginActivity.this, UserConstant.email, response.body().getUserDetail().getEmail().toString());
                        SharedHelper.putKey(LoginActivity.this, UserConstant.organization, response.body().getUserDetail().getOrganization().toString());
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                pd.dismiss();
                if (t instanceof SocketTimeoutException) {
                    userLogin(mobileNumber);
                }
            }
        });
    }*/



}
