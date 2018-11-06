package com.hp.deepak.excelapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class endAdapter2 extends ArrayAdapter<endModel2> {

    private List<endModel2> modelList;
    private Context context;
    private LayoutInflater mInflater;

    public endAdapter2(Context context, List<endModel2> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        modelList = objects;
    }


    @Override
    public endModel2 getItem(int position) {
        return modelList.get(position);
    }

    @Override
    @NonNull
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.end2_model, parent, false);
            vh = endAdapter2.ViewHolder.create((LinearLayout) view);
            view.setTag(vh);
        } else {
            vh = (endAdapter2.ViewHolder) convertView.getTag();
        }

        endModel2 item = getItem(position);

        vh.endItemTV.setText(item.getEndItem());
        vh.partNoTV.setText(item.getPartNumber());
        vh.qtyNoTv.setText(item.getQty());


        return vh.rootView;
    }

    private static class ViewHolder {
        public final LinearLayout rootView;

        public final TextView endItemTV,partNoTV,qtyNoTv;

        private ViewHolder(LinearLayout rootView, TextView endItemTV, TextView partNoTV,TextView qtyNoTv) {
            this.rootView = rootView;
            this.endItemTV = endItemTV;
            this.partNoTV = partNoTV;
            this.qtyNoTv= qtyNoTv;
        }

        public static endAdapter2.ViewHolder create(LinearLayout rootView) {

            TextView endItemTv = rootView.findViewById(R.id.endItem2);
            TextView partNoTV = rootView.findViewById(R.id.partNo2);
            TextView qtyNoTv = rootView.findViewById(R.id.qty);


            return new endAdapter2.ViewHolder(rootView,endItemTv, partNoTV,qtyNoTv);
        }
    }

}