package com.dfanr.androidaccessibilitydemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dfanr.androidaccessibilitydemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = GlobalConstant.LOG_TAG;

    private ActivityMainBinding binding;

    private Button button;
    /** 重写AppCompatActivity的相关方法 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.buttonStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAccessibilityService();
            }
        });

    }

    @Override
    protected void onDestroy() {
        stopAccessibilityService();
        super.onDestroy();
    }

    /** 自定义方法 */
    private Intent getAccessibilityServiceIntent(){
        return new Intent(MainActivity.this, TestAccessibleService.class);
    }

    private void startAccessibilityService(){
        Log.d(TAG, "开启无障碍服务");
        startService(getAccessibilityServiceIntent());
    }

    private void stopAccessibilityService(){
        Log.d(TAG, "关闭无障碍服务");
        stopService(getAccessibilityServiceIntent());
    }


}