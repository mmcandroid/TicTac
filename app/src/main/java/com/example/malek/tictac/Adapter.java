package com.example.malek.tictac;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<Casse> casses ;
    private Context context;

    public void setiClickListenr(IClickListenr iClickListenr) {
        this.iClickListenr = iClickListenr;
    }

    private IClickListenr iClickListenr;

    public Adapter(Context context, List<Casse> casses) {
        this.context = context;
        this.casses = casses;
    }
    public void update(List<Casse> casseList) {
        casses=casseList ;
        this.notifyDataSetChanged();
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Adapter.ViewHolder holder, int position) {
        holder.value.setText(casses.get(position).getValue());
        if (iClickListenr !=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iClickListenr.itemClicked(holder.getAdapterPosition());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return casses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public   TextView value;
        public ViewHolder(View itemView) {
            super(itemView);
             value= (TextView)itemView.findViewById(R.id.value);
        }
    }
}
