package com.example.customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

public class MyAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    List<ItemData> dataList;

    public MyAdapter(Context context, List<ItemData> dataList) {// 장소와 동적 객체 배열을 받음
        this.context = context;
        this.dataList = dataList;
        this.layoutInflater = LayoutInflater.from(context); // 리스트뷰의 아이템 뷰를 위한 레이아웃을 불러오기 위한 작업
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;// 각 아이템을 갖고옴.....?
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item, null);
        }
        ImageView ivPoster = view.findViewById(R.id.image_poster);
        TextView tvTitle = view.findViewById(R.id.poster_title);
        TextView tvSub = view.findViewById(R.id.poster_sub);

        //리스트뷰 position에 따른 데이터를 로딩하기 위한 작업
        ItemData itemData = dataList.get(position);

        ivPoster.setImageResource(itemData.getImgId());
        tvTitle.setText(itemData.getTitle());
        tvSub.setText(itemData.getSub());
        return view;
    }
}
