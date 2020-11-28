package com.rm.myapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rm.myapp.R;
import com.rm.myapp.helper.SharedHelper;
import com.rm.myapp.helper.UserConstant;
import com.rm.myapp.model.AllUserModel;
import com.rm.myapp.model.DataModel;
import com.rm.myapp.model.DistanceUserModel;
import com.rm.myapp.retrofit.ApiInterface;
import com.rm.myapp.retrofit.AppConfig;

import java.net.SocketTimeoutException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context context;
    private DistanceUserModel model;
    private String myLatitude;
    private String myLongitute;

    public UserAdapter( Context context,DistanceUserModel model,String myLatitude,String myLongitute) {
        this.context = context;
        this.model=model;
        this.myLatitude = myLatitude;
        this.myLongitute = myLongitute;
    }

    @NonNull
    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, int position) {

        holder.nametxt.setText(model.getUserList().get(position).getUserName());
      //  holder.gendertxt.setText(model.getUserList().get(position).get());
        holder.phonetxt.setText(model.getUserList().get(position).getPhoneNumber());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendLocation(context,myLatitude,myLongitute);
            }
        });

    }

    @Override
    public int getItemCount() {
        return model.getUserList().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  {
        @BindView(R.id.ll)
        LinearLayout ll;
        @BindView(R.id.nametxt)
        TextView nametxt;
        @BindView(R.id.gendertxt)
        TextView gendertxt;
        @BindView(R.id.phonetxt)
        TextView phonetxt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static void sendLocation(Context context, String myLatitude, String myLongitute) {
        ApiInterface apiInterface1 = AppConfig.getRetrofit().create(ApiInterface.class);
        String token = SharedHelper.getKey(context, UserConstant.token);
        Call<DataModel> call = apiInterface1.sendLocation(AppConfig.Key,token, myLatitude, myLongitute);
        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {

               // System.out.println("+++++++ "+response.body().getMessage());
                if (response.isSuccessful()) {
                    Toast.makeText( context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {
                    t.getMessage();
                if (t instanceof SocketTimeoutException) {
                    sendLocation(context,myLatitude,myLongitute);
                }
            }
        });
    }

}
