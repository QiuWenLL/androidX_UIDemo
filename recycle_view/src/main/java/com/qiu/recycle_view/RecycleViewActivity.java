package com.qiu.recycle_view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    RecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);


        recyclerView=findViewById(R.id.recycleView);

        List<String> list=new ArrayList<>();

        for (int i=0;i<1200;i++){

            list.add("测试"+i);

        }

        adapter=new RecycleAdapter(RecycleViewActivity.this,list);
        recyclerView.setAdapter(adapter);

        //线性布局
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(RecycleViewActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);


        //线性布局 HORIZONTAL
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(RecycleViewActivity.this);
//        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
//        recyclerView.setLayoutManager(linearLayoutManager);


        //grid 布局
//        GridLayoutManager layoutManager = new GridLayoutManager(RecycleViewActivity.this,2);
////        layoutManager.setOrientation(RecyclerView.HORIZONTAL); // 也能设置横向滚动
//        recyclerView.setLayoutManager(layoutManager);





        adapter.RegisterOnItemOnclickCallBack(new IItemOnclick() {
            @Override
            public void onClick(View view, int position) {
                String tempStr=list.get(position);
                list.set(position,tempStr+"我点击了"+position);
                runOnUiThread(()->{
                    adapter.notifyDataSetChanged();
                });
            }
        });

    }
}