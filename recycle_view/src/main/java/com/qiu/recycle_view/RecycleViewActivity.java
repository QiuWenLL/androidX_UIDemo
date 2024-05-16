package com.qiu.recycle_view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    RecycleAdapter adapter;

    List<String> CacheList =new ArrayList<>();


    private SmartRefreshLayout refreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);


        InitView();

        OnClick();

        InitData();


    }

    /**
     *
     * 初始化 ui
     */
    private void InitView(){


        refreshLayout=findViewById(R.id.refreshLayout);

        recyclerView=findViewById(R.id.recycleView);

        DataModel.getInstance().InitData();

        //注册刷新数据
        DataModel.getInstance().RegisterIRefreshData(RefreshDataCallBack());


        adapter=new RecycleAdapter(RecycleViewActivity.this, CacheList);
        recyclerView.setAdapter(adapter);



//region布局设置
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

//endregion


    }


    /**
     * 点击事件
     */
    private void OnClick(){
        findViewById(R.id.btnMusic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataModel.getInstance().InitMusicData();

            }
        });

        findViewById(R.id.btnTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataModel.getInstance().InitTestData();

            }
        });

    }


    private void InitData(){

        LoadMoreData(0,10,()->{});

        adapter.RegisterOnItemOnclickCallBack(new IItemOnclick() {
            @Override
            public void onClick(View view, int position) {
                String tempStr= CacheList.get(position);
                CacheList.set(position,tempStr+"我点击了"+position);
                runOnUiThread(()->{
                    adapter.notifyDataSetChanged();
                });
            }
        });



        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
                Toast.makeText(RecycleViewActivity.this,"刷新",Toast.LENGTH_SHORT).show();

            }
        });


        //加载更多
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                LoadMoreData(CacheList.size(),CacheList.size()+10,()->{
                    refreshLayout.finishLoadMore(1000);
                });
//                Toast.makeText(RecycleViewActivity.this,"加载更多",Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void LoadMoreData(int startIndex, int endIndex,Runnable runnable){


        if(startIndex>DataModel.getInstance().getDatas().size())
            startIndex=DataModel.getInstance().getDatas().size();

        if(endIndex>DataModel.getInstance().getDatas().size())
            endIndex=DataModel.getInstance().getDatas().size();

        if(startIndex==endIndex)
        {
            Toast.makeText(RecycleViewActivity.this,"没有更多数据了！",Toast.LENGTH_SHORT).show();
            runnable.run();
            return;
        }

        for (int i=startIndex;i<endIndex;i++){
            CacheList.add(DataModel.getInstance().getDatas().get(i));
        }

        adapter.notifyDataSetChanged();

        runnable.run();

    }

    private void InitCacheData(){
        if(CacheList.size()>0)
            CacheList.clear();

        LoadMoreData(0,10,()->{});


    }

    private IRefreshData RefreshDataCallBack(){
        return new IRefreshData() {
            @Override
            public void onRefresh() {

                InitCacheData();

            }
        };
    }



}