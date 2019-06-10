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

public class wireListAdapter extends ArrayAdapter<wireListModel> {

    private List<wireListModel> modelList;
    private Context context;
    private LayoutInflater mInflater;

    public wireListAdapter(Context context, List<wireListModel> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        modelList = objects;
    }


    @Override
    public wireListModel getItem(int position) {
        return modelList.get(position);
    }

    @Override
    @NonNull
    public View getView(int position, View convertView, ViewGroup parent) {
        final wireListAdapter.ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.wirelist_model, parent, false);
            vh = wireListAdapter.ViewHolder.create((LinearLayout) view);
            view.setTag(vh);
        } else {
            vh = (wireListAdapter.ViewHolder) convertView.getTag();
        }

        wireListModel item = getItem(position);

        vh.wireTV.setText(item.getWire());
        vh.multicoreTV.setText(item.getMulticore());
        vh.partNoTV.setText(item.getPartno());
        vh.end1.setText(item.getEndID1());
        vh.pin1.setText(item.getPin1());
        vh.term1Tv.setText(item.getTerm1());
        vh.end2.setText(item.getEndID2());
        vh.pin2.setText(item.getPin2());
        vh.term2.setText(item.getTerm2());
        vh.design.setText(item.getDesign());
        vh.length.setText(item.getLength());


        return vh.rootView;
    }

    private static class ViewHolder {
        public final LinearLayout rootView;

        public final TextView wireTV,multicoreTV,partNoTV,end1,pin1,term1Tv,end2,pin2,term2,length,design;

        private ViewHolder(LinearLayout rootView, TextView wireTV, TextView multicoreTV,TextView partNOTV,TextView end1,TextView pin1,
                   TextView term1Tv, TextView end2,TextView pin2,TextView term2,TextView length,TextView design) {

            this.rootView = rootView;
            this.wireTV = wireTV;
            this.multicoreTV=multicoreTV;
            this.partNoTV= partNOTV;
            this.end1=end1;
            this.pin1=pin1;
            this.term1Tv=term1Tv;
            this.end2=end2;
            this.pin2=pin2;
            this.term2=term2;
            this.length=length;
            this.design=design;
        }

        public static wireListAdapter.ViewHolder create(LinearLayout rootView) {

            TextView wireTv = rootView.findViewById(R.id.wireTV);
            TextView multicoreTV = rootView.findViewById(R.id.multicoreTV);
            TextView partNOTV =  rootView.findViewById(R.id.partnumTV);
            TextView end1TV = rootView.findViewById(R.id.endID1TV);
            TextView pin1TV = rootView.findViewById(R.id.pin1TV);
            TextView term1TV =  rootView.findViewById(R.id.term1TV);
            TextView end2TV = rootView.findViewById(R.id.endID2TV);
            TextView pin2TV = rootView.findViewById(R.id.pin2TV);
            TextView term2TV =  rootView.findViewById(R.id.term2TV);
            TextView lengthTV = rootView.findViewById(R.id.lengthTV);
            TextView designTV = rootView.findViewById(R.id.designTV);

            return new wireListAdapter.ViewHolder(rootView,wireTv,multicoreTV,partNOTV,end1TV,pin1TV,term1TV,end2TV,pin2TV,term2TV,lengthTV,designTV);
        }
    }

}
