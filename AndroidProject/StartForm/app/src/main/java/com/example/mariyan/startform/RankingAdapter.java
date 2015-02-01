package com.example.mariyan.startform;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mariyan on 30.1.2015 г..
 */
public class RankingAdapter extends BaseAdapter {

    private Context context;
    private List<User> users;

    public RankingAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }


    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        public ImageView imageView;
        public TextView nickname;
        public TextView time;
        public TextView rankingPosition;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_list_view, null);
            viewHolder = new ViewHolder();
            viewHolder.nickname = (TextView) view.findViewById(R.id.name);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.icon);
            viewHolder.time = (TextView) view.findViewById(R.id.scores);
            viewHolder.rankingPosition = (TextView) view.findViewById(R.id.ranking_position);
            view.setTag(viewHolder);

        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }



        User user = (User) getItem(position);

        viewHolder.imageView.setImageDrawable(user.getImageView().getDrawable());
        viewHolder.nickname.setText(user.getNickname());
        viewHolder.time.setText(user.getTime() + "");
        viewHolder.rankingPosition.setText(position + 1 + "");


        return view;
    }
}
