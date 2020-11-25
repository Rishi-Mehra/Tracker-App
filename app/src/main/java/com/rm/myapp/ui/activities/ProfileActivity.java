package com.rm.myapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

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
import com.rm.myapp.helper.UserConstant;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.etxt)
    EditText etxt;
    @BindView(R.id.radioSex)
    RadioGroup radioSex;
    @BindView(R.id.saveBtn)
    Button saveBtn;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        initUI();
    }

    private void init(){
        ButterKnife.bind(this);
    }

    private void initUI(){
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etxt.getText().toString();
                int selectedId = radioSex.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);
                String gender = radioButton.getText().toString();
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(ProfileActivity.this, "Enter Name!", Toast.LENGTH_SHORT).show();
                }else {
                    SharedHelper.putKey(ProfileActivity.this, UserConstant.name,name);
                    SharedHelper.putKey(ProfileActivity.this, UserConstant.gender,gender);
                    SharedHelper.putBooleanKey(ProfileActivity.this,UserConstant.isLogin,true);
                    startActivity(new Intent(ProfileActivity.this,MainActivity.class));
                    finish();
                }
            }
        });
    }
}