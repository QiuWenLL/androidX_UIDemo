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


    private List<String> datas=new ArrayList<>();

    public List<String> getDatas() {
        return datas;
    }

    public void InitData(){

        for (int i=0;i<100;i++){

            datas.add("测试"+i);

        }

    }


}
