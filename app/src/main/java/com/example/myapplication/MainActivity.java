package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.example.myapplication.utils.Utils;

public class MainActivity extends AppCompatActivity {
    private Spinner type;
    private Spinner date;
    private Spinner time;
    private Button querenBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        querenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date1 = date.getSelectedItem().toString().trim();
                String type1 = type.getSelectedItem().toString().trim();
                String time1 = time.getSelectedItem().toString().trim();
                if ("餐桌类型选择".equals(type1)&&"未来7天的日期列表".equals(date1)&&"选择时间段".equals(time1)) {
                    System.out.println("没有选择相对应的条目");
                    System.out.println("12345689");
                } else {
                    Utils.putData("type",type1,getApplicationContext());
                    Utils.putData("date",date1,getApplicationContext());
                    Utils.putData("time",time1,getApplicationContext());
                    Intent intent = new Intent(MainActivity.this,DcActivity.class);
                    startActivity(intent);
                    System.out.println(date1);
                    System.out.println(time1);
                    System.out.println(type1);
                }


            }
        });
    }

    private void initView() {
        type = (Spinner) findViewById(R.id.type);
        date = (Spinner) findViewById(R.id.date);
        time = (Spinner) findViewById(R.id.time);
        querenBtn = (Button) findViewById(R.id.queren_btn);
    }
}