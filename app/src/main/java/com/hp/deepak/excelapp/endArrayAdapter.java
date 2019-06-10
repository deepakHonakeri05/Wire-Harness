package com.hp.deepak.excelapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class endArrayAdapter extends ArrayAdapter<endListModel> {

    private List<endListModel> modelList;
    private Context context;
    private LayoutInflater mInflater;

    public endArrayAdapter(Context context, List<endListModel> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        modelList = objects;
    }


    @Override
    public endListModel getItem(int position) {
        return modelList.get(position);
    }

    @Override
    @NonNull
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.endlist_model, parent, false);
            vh = endArrayAdapter.ViewHolder.create((LinearLayout) view);
            view.setTag(vh);
        } else {
            vh = (endArrayAdapter.ViewHolder) convertView.getTag();
        }

        endListModel item = getItem(position);

        vh.endItemTV.setText(item.getEndItem());
        vh.partNoTV.setText(item.getPartNumber());
        vh.QtyTV.setText(item.getQty1());


        return vh.rootView;
    }

    private static class ViewHolder {
        public final LinearLayout rootView;

        public final TextView endItemTV;
        public final TextView partNoTV;
        public final TextView QtyTV;

        private ViewHolder(LinearLayout rootView,TextView endItemTV, TextView partNoTV,TextView QtyTV) {
            this.rootView = rootView;
            this.endItemTV = endItemTV;
            this.partNoTV = partNoTV;
            this.QtyTV= QtyTV;
        }

        public static endArrayAdapter.ViewHolder create(LinearLayout rootView) {

            TextView endItemTv = rootView.findViewById(R.id.endItemTV);
            TextView partNoTV = rootView.findViewById(R.id.partNoTV);
            TextView QtyTV =  rootView.findViewById(R.id.EQtyTV);


            return new endArrayAdapter.ViewHolder(rootView,endItemTv, partNoTV, QtyTV);
        }
    }

}
