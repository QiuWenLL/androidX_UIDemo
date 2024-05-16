package com.qiu.recycle_view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHoder> {

    private List<String> data;

    private Context context;

    private IItemOnclick itemOnclick;

    public RecycleAdapter(Context _context, List<String> list) {
        context = _context;
        data = list;
    }

    public void RegisterOnItemOnclickCallBack(IItemOnclick onclick){
        itemOnclick=onclick;
    }
    public void  UnRegisterOnItemOnclickCallBack(){
        itemOnclick=null;
    }

    @NonNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_view_layout, null);
        MyViewHoder myViewHoder = new MyViewHoder(view);
        return myViewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoder holder, final int position) {
//        News news = mNewsList.get(position);
//        holder.mTitleTv.setText(news.title);
//        holder.mTitleContent.setText(news.content);
//        holder.mRootView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this,"点击了："+ position,Toast.LENGTH_LONG).show();
//            }
//        });

        holder.tex.setText(data.get(position));

        int index=position;

        holder.tex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemOnclick != null)
                    itemOnclick.onClick(holder.root, index);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyViewHoder extends RecyclerView.ViewHolder {

        TextView tex;
        View root;

        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            root = itemView;
            tex = root.findViewById(R.id.textView);


        }
    }


}
