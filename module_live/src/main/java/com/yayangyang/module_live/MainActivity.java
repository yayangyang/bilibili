package com.yayangyang.module_live;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Bean> mBeans=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        mRecyclerView = findViewById(R.id.rc);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.dp_13);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2,dimensionPixelSize,true));
        MyAdapter myAdapter = new MyAdapter(R.layout.my_item,mBeans);
//        MyNormalAdapter myAdapter = new MyNormalAdapter(this,mBeans);
        mRecyclerView.setAdapter(myAdapter);
    }

    private void initData() {
        for(int i=0;i<6;i++){
            mBeans.add(new Bean());
        }
    }
}
