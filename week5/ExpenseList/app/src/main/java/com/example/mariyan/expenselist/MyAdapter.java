package com.example.mariyan.expenselist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mariyan on 19.11.2014 г..
 */
public class MyAdapter extends BaseAdapter {
//    private LayoutInflater inflater;
    private List<Bills> list;
    private Context context;

    public MyAdapter(List<Bills> list, Context context) {
        this.list = list;
//        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    private class ViewHolder {
        public TextView label;
        public TextView price;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        View view;
        ViewHolder viewHolder;
        if(convertView == null) {
//            view = inflater.inflate(R.layout.layout, viewGroup, false);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout, null);
            viewHolder = new ViewHolder();
            viewHolder.label = (TextView) view.findViewById(R.id.label);
            viewHolder.price = (TextView) view.findViewById(R.id.price);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        Bills bills = list.get(position);
        viewHolder.label.setText(bills.getLabel());
        viewHolder.price.setText(bills.getPrice() + "");



        view.findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Delete");
                builder.setMessage("Are you want to delete item on position " + (position+1) + "?");
                final int positionToDelete = position;
                builder.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        list.remove(positionToDelete);
                        notifyDataSetChanged();

                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();
            }
        });


        return view;
    }

}
