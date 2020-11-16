package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class DcActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private Button left;
    private int index;
    private Button right;
    private Handler handler = new Handler();

    private List<ImageView> imageViewList;
    private LinearLayout mLl;
    private ListView mListView;
    private TextView mSumMoneyTv;
    private Button mJzBtn;
    private int caiNum;
    private int sumNum;
    private int sumMoney;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dc);
        initView();
        setPager();
        setListView();
        mJzBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int money = Utils.getIntData("sumMoney", getApplicationContext());
                if (money > 0) {
                    Intent intent = new Intent(DcActivity.this, ZhifuActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "没有点菜", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 向ListView添加数据
     */
    private void setListView() {
        mListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 4;
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
                    view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dc_item, parent, false);
                } else {
                    view = convertView;
                }

                ImageView mImage = (ImageView) view.findViewById(R.id.image);
                final TextView mMoney = (TextView) view.findViewById(R.id.money);
                Button mSubtractBtn = (Button) view.findViewById(R.id.subtract_btn);
                final TextView mNumTv = (TextView) view.findViewById(R.id.num_tv);
                Button mAddBtn = (Button) view.findViewById(R.id.add_btn);
                mImage.setBackgroundResource(images[0]);
                mAddBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        当点击加号按钮的时候,总和进行++
                        int num = Integer.parseInt(mNumTv.getText().toString());
                        mNumTv.setText(++num + "");
                        sumNum++;
                        System.out.println(sumNum + "总和");
                        /**
                         * 每当点击了一次添加数据,则将这个数据存入当本地文件当中,算出总价来,然后将这个总价数据存入到本地文件当中
                         */
                        int money = Integer.parseInt(mMoney.getText().toString());
                        sumMoney += num * money;

                        Utils.putData("sumNum", sumNum,getApplicationContext());
                        Utils.putData("sumMoney", sumMoney,getApplicationContext());

                        System.out.println(sumMoney + "总和价格");
//                        添加总价的地方
                        mSumMoneyTv.setText("总价:" + sumMoney);
                        if (num > 0 && num <= 1) {
                            caiNum++;
                            /**
                             * 显示有几道菜
                             */
                            Utils.putData("caiNum",caiNum,getApplicationContext());
                        }

                    }
                });
                mSubtractBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int num = Integer.parseInt(mNumTv.getText().toString());
                        if (num > 0) {
                            mNumTv.setText(--num + "");
                            sumNum--;
                            int money = Integer.parseInt(mMoney.getText().toString());
                            sumMoney -= money * num;
                            Utils.putData("sumNum", sumNum,getApplicationContext());
                            Utils.putData("sumMoney", sumMoney,getApplicationContext());

                            mSumMoneyTv.setText("总价:" + sumMoney);
                        }
                        System.out.println(sumNum + "总和数量");
                        System.out.println(sumMoney + "总和价格");
                    }
                });

                int num = Integer.parseInt(mNumTv.getText().toString());
                return view;
            }
        });
    }

    private void setPager() {
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ImageView imageView = imageViewList.get(position);
                ViewParent parent = imageView.getParent();
                if (parent != null) {
                    ViewGroup viewGroup = (ViewGroup) parent;
                    viewGroup.removeView(imageView);
                }
                container.addView(imageView);
                return imageView;
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    index++;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            viewPager.setCurrentItem(index%2,true);
                        }
                    });
                    SystemClock.sleep(1000);
                }
            }
        }).start();

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                    left.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (position == 1) {
                                viewPager.setCurrentItem(0,true);
                            }
                        }
                    });
                    right.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (position == 0) {
                                viewPager.setCurrentItem(1,true);
                            }
                        }
                    });

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        left = (Button) findViewById(R.id.left);
        right = (Button) findViewById(R.id.right);
        imageViewList = new ArrayList<>();
        mLl = (LinearLayout) findViewById(R.id.ll);
        mListView = (ListView) findViewById(R.id.listView);
        mSumMoneyTv = (TextView) findViewById(R.id.sumMoney_tv);
        mJzBtn = (Button) findViewById(R.id.jz_btn);
        for (int i = 0; i < images.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(images[i]);
            imageViewList.add(imageView);
        }

    }
}
