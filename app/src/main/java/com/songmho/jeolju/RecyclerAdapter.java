package com.songmho.jeolju;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by songmho on 2015-05-30.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context context;
    String[] drawer_list_list;
    List<RecyclerItem> items;
    int item_drawerlist;

    public RecyclerAdapter(Context context, List<RecyclerItem> drawer_list_list, int item_drawerlist) {
        this.context=context;
        this.items=drawer_list_list;
        this.item_drawerlist=item_drawerlist;
    }
    public RecyclerAdapter(Context context, String[] drawer_list_list, int item_drawerlist) {
        this.context=context;
        this.drawer_list_list=drawer_list_list;
        this.item_drawerlist=item_drawerlist;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        public LinearLayout container;
        CardView cardView;
        TextView beer;
        TextView beer_money;
        TextView soju;
        TextView soju_money;
        TextView cock;
        TextView cock_money;
        TextView etc;
        TextView etc_money;
        TextView sum;
        TextView date;

        @Override
        public String toString() {
            return super.toString();
        }

        public ViewHolder(View itemView,int itemlayout) {
            super(itemView);
            switch (itemlayout){
                case R.layout.item_drawerlist:
                    title=(TextView)itemView.findViewById(R.id.title);
                    container=(LinearLayout)itemView.findViewById(R.id.container);
                    break;

                case R.layout.item_list:
                    cardView=(CardView)itemView.findViewById(R.id.cardview);
                    beer=(TextView)itemView.findViewById(R.id.beer);
                    beer_money=(TextView)itemView.findViewById(R.id.beer_money);
                    soju=(TextView)itemView.findViewById(R.id.soju);
                    soju_money=(TextView)itemView.findViewById(R.id.soju_money);
                    cock=(TextView)itemView.findViewById(R.id.cock);
                    cock_money=(TextView)itemView.findViewById(R.id.cock_money);
                    etc=(TextView)itemView.findViewById(R.id.etc);
                    etc_money=(TextView)itemView.findViewById(R.id.etc_money);
                    sum=(TextView)itemView.findViewById(R.id.sum);
                    date=(TextView)itemView.findViewById(R.id.date);
                    break;


            }
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(item_drawerlist==R.layout.item_drawerlist) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drawerlist, parent, false);
            return new ViewHolder(v, item_drawerlist);
        }

        else if(item_drawerlist==R.layout.item_list){
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
            return new ViewHolder(view, item_drawerlist);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        switch (item_drawerlist){
            case R.layout.item_drawerlist:
                holder.title.setText(drawer_list_list[position]);
                holder.container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent;
                        switch (position){
                            case 0:
                                intent = new Intent(context, ListActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                                break;
                            case 1:
                                Toast.makeText(context,"준비중입니다.ㅠㅜ",Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(context,"준비중입니다.ㅠㅜ",Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                intent = new Intent(context, AboutActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                                break;

                        }
                    }
                });
                break;

            case R.layout.item_list:
                RecyclerItem item=items.get(position);
                holder.itemView.setTag(item);
                holder.beer.setText("" + item.getBeer_int()+"병");
                holder.beer_money.setText(""+item.getBeer_money_int()+"원");
                holder.soju.setText(""+item.getSoju_int()+"병");
                holder.soju_money.setText(""+item.getSoju_money_int()+"원");
                holder.cock.setText(""+item.getCock_int()+"병");
                holder.cock_money.setText(""+item.getCock_money_int()+"원");
                holder.etc.setText(""+item.getEtc_int()+"병");
                holder.etc_money.setText(""+item.getEtc_money_int()+"원");
                holder.sum.setText(""+item.getSum()+"원");
                holder.date.setText(item.getDate());

                break;

        }



    }

    @Override
    public int getItemCount() {
        switch (item_drawerlist){
            case R.layout.item_drawerlist:
                return drawer_list_list.length;
            case R.layout.item_list:
                return items.size();
        }
        return 0;
    }
}
