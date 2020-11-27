package com.rm.myapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rm.myapp.R;
import com.rm.myapp.helper.SharedHelper;
import com.rm.myapp.helper.TransparentProgressDialog;
import com.rm.myapp.model.DataModel;
import com.rm.myapp.retrofit.ApiInterface;
import com.rm.myapp.retrofit.AppConfig;

import java.net.SocketTimeoutException;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.rm.myapp.helper.UserConstant.email;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

   private Unbinder unbinder;
    @BindView(R.id.mobile_etxt)
    EditText mobile_etxt;
    @BindView(R.id.register_btn)
    Button register_btn;
    @BindView(R.id.registerTxt)
    TextView registerTxt;
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
        pd = new TransparentProgressDialog(this, R.drawable.spinner);

    }

    private void initUI() {
        register_btn.setOnClickListener(this);
        registerTxt.setOnClickListener(this);

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
                    userLogin(mobileNumber);

                }
                break;
            case R.id.registerTxt:
                startActivity(new Intent(this,RegisterActivity.class));
                break;

        }
    }

    private void userLogin(String mobileNumber){
        pd.show();
        ApiInterface apiInterface1 = AppConfig.getRetrofit().create(ApiInterface.class);
        Call<DataModel> call = apiInterface1.loginUser(AppConfig.Key,mobileNumber);
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {
                pd.dismiss();
                if (response.isSuccessful()){
                    String otp =response.body().getData().toString();
                    System.out.println("++++== "+otp);
                    Intent intent = new Intent(LoginActivity.this,OtpActivity.class);
                    intent.putExtra("otp",otp);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                pd.dismiss();
                if (t instanceof SocketTimeoutException) {
                    userLogin(mobileNumber);
                }
            }
        });
    }



}
