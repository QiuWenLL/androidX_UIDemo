package com.qiu.recycle_view;

import java.util.ArrayList;
import java.util.List;

public class DataModel {

    private static DataModel dataModel;

    public static DataModel getInstance(){

        if(dataModel==null){
            synchronized (DataModel.class){
                dataModel=new DataModel();
            }
        }

        return dataModel;
    }

    private IRefreshData iRefreshData;

    public void RegisterIRefreshData(IRefreshData refreshData){
        iRefreshData=refreshData;
    }
    public void UnRegisterIRefreshData( ){
        iRefreshData=null;
    }

    private List<String> datas=new ArrayList<>();

    public List<String> getDatas() {
        return datas;
    }


    public void InitData(){


        for (int i=0;i<100;i++){

            datas.add("测试 "+i);

        }

    }


    public void InitMusicData(){


        if(datas.size()>0)
            datas.clear();

        for (int i=0;i<100;i++){

            datas.add("歌曲 "+i);

        }

        iRefreshData.onRefresh();

    }



    public void InitTestData(){

        if(datas.size()>0)
            datas.clear();

        for (int i=0;i<100;i++){

            datas.add("test "+i);

        }

        iRefreshData.onRefresh();

    }




}
