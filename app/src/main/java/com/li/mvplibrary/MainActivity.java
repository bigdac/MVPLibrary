package com.li.mvplibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.li.baselibrary.base.MvpBaseActivity;
import com.li.baselibrary.inject.InjectModel;
import com.li.baselibrary.inject.InjectPresenter;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends MvpBaseActivity {
    TextView textView;
    @InjectPresenter
    private UserPresent userPresent;

    @Override
    protected void initAllConfig() {
        textView = findViewById(R.id.tv_text);
        String s = userPresent.getMessage()+userPresent.getMessage2();
        textView.setText(s);
        Request request = new Request.Builder().build();
        OkHttpClient okHttpClient  = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }


}
