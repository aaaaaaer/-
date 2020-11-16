package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class JzActivity extends AppCompatActivity {
    private TextView mDateTv;
    private TextView mTimeTv;
    private TextView mTypeTv;
    private ListView mListView;
    private int caiNum;
    private TextView mSummoneyTv;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jz_activity);
        initView();
        setData();
    }

    /**
     * 添加数据
     */
    private void setData() {
        String date = sharedPreferences.getString("date","Nan");
        String time = sharedPreferences.getString("time","Nan");
        String type = sharedPreferences.getString("type","Nan");
        mDateTv.setText(date + "    日期");
        mTimeTv.setText(time + "    时间段");
        mTypeTv.setText(type + "    餐桌类型");
        /**
         * 添加ListView
         */
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
                    view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.zf_item,parent,false);
                } else {
                    view = convertView;
                }
                return view;
            }
        });
    }

    private void initView() {
        mDateTv = (TextView) findViewById(R.id.date_tv);
        mTimeTv = (TextView) findViewById(R.id.time_tv);
        mTypeTv = (TextView) findViewById(R.id.type_tv);
        mListView = (ListView) findViewById(R.id.listView);
        mSummoneyTv = (TextView) findViewById(R.id.summoney_tv);
        sharedPreferences = getSharedPreferences("config",0);
        caiNum = sharedPreferences.getInt("caiNum",0);
        int sum = sharedPreferences.getInt("sumMoney",0);
        mSummoneyTv.setText("总价:" + sum);
    }
}
