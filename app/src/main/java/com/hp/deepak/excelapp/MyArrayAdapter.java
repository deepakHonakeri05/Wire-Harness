package com.hp.deepak.excelapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<MyDataModel> {

   private List<MyDataModel> modelList;
   private Context context;
    private LayoutInflater mInflater;

    // Constructors
    public MyArrayAdapter(Context context, List<MyDataModel> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        modelList = objects;
    }

    @Override
    public MyDataModel getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_row_view, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        MyDataModel item = getItem(position);

        vh.part_noTV.setText(item.getPartno());
        vh.qty_TV.setText(item.getQty());
        vh.uom_TV.setText(item.getUom());
        vh.desc_TV.setText(item.getDesc());
        vh.baps_TV.setText(item.getBaps());

        return vh.rootView;
    }

    private static class ViewHolder {
        public final RelativeLayout rootView;

        public final TextView part_noTV;
        public final TextView qty_TV,uom_TV,desc_TV,baps_TV;

        private ViewHolder(RelativeLayout rootView, TextView part_noTV, TextView qty_TV,TextView uom_TV,TextView desc_TV,TextView baps_TV) {
            this.rootView = rootView;
            this.part_noTV = part_noTV;
            this.qty_TV = qty_TV;
            this.uom_TV= uom_TV;
            this.desc_TV=desc_TV;
            this.baps_TV=baps_TV;
        }

        public static ViewHolder create(RelativeLayout rootView) {
            TextView part_noTV = rootView.findViewById(R.id.part_noTV);
            TextView qty_TV = rootView.findViewById(R.id.qtyTV);
            TextView uom_TV = rootView.findViewById(R.id.uomTV);
            TextView desc_TV = rootView.findViewById(R.id.descTV);
            TextView baps_TV = rootView.findViewById(R.id.bapsTV);
            return new ViewHolder(rootView, part_noTV, qty_TV,uom_TV,desc_TV,baps_TV);
        }
    }
}