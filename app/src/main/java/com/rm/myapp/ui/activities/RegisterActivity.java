package com.rm.myapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.rm.myapp.R;
import com.rm.myapp.helper.SharedHelper;
import com.rm.myapp.helper.TransparentProgressDialog;
import com.rm.myapp.helper.UserConstant;
import com.rm.myapp.model.DataModel;
import com.rm.myapp.retrofit.ApiInterface;
import com.rm.myapp.retrofit.AppConfig;

import java.net.SocketTimeoutException;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.etxt)
    EditText etxt;
    @BindView(R.id.mobile_etxt)
    EditText mobile_etxt;
    @BindView(R.id.passwordEtxtx)
    EditText passwordEtxtx;
    @BindView(R.id.radioSex)
    RadioGroup radioSex;
    @BindView(R.id.saveBtn)
    Button saveBtn;
    RadioButton radioButton;
    TransparentProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        initUI();
    }

    private void init(){
        ButterKnife.bind(this);
        pd = new TransparentProgressDialog(this, R.drawable.spinner);
    }

    private void initUI(){
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etxt.getText().toString().trim();
                String mobile = mobile_etxt.getText().toString().trim();
                String password = passwordEtxtx.getText().toString().trim();
                int selectedId = radioSex.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                String gender = radioButton.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(RegisterActivity.this, "Enter Name!", Toast.LENGTH_SHORT).show();
                }else  if (TextUtils.isEmpty(mobile)){
                    Toast.makeText(RegisterActivity.this, "Enter Mobile Number!", Toast.LENGTH_SHORT).show();
                }else  if (mobile.length()<10){
                    Toast.makeText(RegisterActivity.this, "Enter valid mobile number!", Toast.LENGTH_SHORT).show();
                }else  if (TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this, "Enter Password!", Toast.LENGTH_SHORT).show();
                }else {
                    registerUser(name,mobile,password,gender);
                }
            }
        });
    }

    private void registerUser(String name, String mobile, String password, String gender) {
        pd.show();
        System.out.println("++++ "+name+"  "+ mobile+"    "+password+" "+ gender);
        ApiInterface apiInterface1 = AppConfig.getRetrofit().create(ApiInterface.class);
        Call<DataModel> call = apiInterface1.registerUser(AppConfig.Key,name, mobile, password, gender );
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(@NotNull Call<DataModel> call, Response<DataModel> response) {
                pd.dismiss();
                if (response.isSuccessful()) {
                    SharedHelper.putKey(RegisterActivity.this, UserConstant.name,name);
                    SharedHelper.putKey(RegisterActivity.this, UserConstant.gender,gender);
                    SharedHelper.putBooleanKey(RegisterActivity.this,UserConstant.isLogin,true);
                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                pd.dismiss();
                System.out.println("++++++ "+t.getMessage());
                if (t instanceof SocketTimeoutException) {
                    registerUser(name, mobile, password, gender);
                }
            }
        });


    }
}