package com.songmho.jeolju;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by songmho on 2015-05-30.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context context;
    String[] drawer_list_list;
    int item_drawerlist;
    public RecyclerAdapter(Context context, String[] drawer_list_list, int item_drawerlist) {
        this.context=context;
        this.drawer_list_list=drawer_list_list;
        this.item_drawerlist=item_drawerlist;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        public LinearLayout container;

        @Override
        public String toString() {
            return super.toString();
        }

        public ViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.title);
            container=(LinearLayout)itemView.findViewById(R.id.container);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drawerlist,parent,false);

        return  new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
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
                        break;
                    case 2:
                        break;
                    case 3:
                        intent = new Intent(context, AboutActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        break;

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return drawer_list_list.length;
    }
}
