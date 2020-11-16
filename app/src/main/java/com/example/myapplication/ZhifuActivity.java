package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.utils.Utils;

import java.util.Timer;
import java.util.TimerTask;

public class ZhifuActivity extends AppCompatActivity {
    private LinearLayout mLl1;
    private TextView mTaskTv;
    private LinearLayout mLl2;
    private int caiNum;
    private TextView mMoneyTv;
    private ListView mListView;
    private Button mZfBtn;
    private int i;
    private int flag;
    private Handler handler = new Handler();
    private int sumNum;
    private int sumMoney;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhifu);
        initView();
        setDate();
        setListView();
        /**
         * 点击支付按钮,跳转到另一个平台
         */
        mZfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZhifuActivity.this,JzActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setListView() {
        mListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return caiNum;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view;
                if (convertView == null) {
                    view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.zf_item, parent, false);
                } else {
                    view = convertView;
                }
                ImageView mImage = (ImageView) view.findViewById(R.id.image);

                Button mSubtractBtn = (Button) view.findViewById(R.id.subtract_btn);
                TextView mNumTv = (TextView) view.findViewById(R.id.num_tv);

                return view;
            }
        });
    }

    private void setDate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (i = 60; i > 0; i--) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mTaskTv.setText(i + "");
                        }
                    });
                    SystemClock.sleep(1000);
                }
                finish();
            }
        }).start();
        int money = Utils.getIntData("sumMoney",getApplicationContext());
        mMoneyTv.setText("总价"+money);
    }

    private void initView() {
        mLl1 = (LinearLayout) findViewById(R.id.ll1);
        mTaskTv = (TextView) findViewById(R.id.task_tv);
        mLl2 = (LinearLayout) findViewById(R.id.ll2);
        mListView = (ListView) findViewById(R.id.listView);
        mZfBtn = (Button) findViewById(R.id.zf_btn);

        flag = 60;
        mMoneyTv = (TextView) findViewById(R.id.money_tv);

        caiNum = Utils.getIntData("caiNum",getApplicationContext());
    }
}
