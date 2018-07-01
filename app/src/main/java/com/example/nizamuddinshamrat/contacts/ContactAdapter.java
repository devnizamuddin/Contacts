package com.example.nizamuddinshamrat.contacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<PersonInfo> personInfos;
    private ClickListener clickListener;

    public ContactAdapter(Context context, ArrayList<PersonInfo> personInfos, ClickListener clickListener) {
        this.context = context;
        this.personInfos = personInfos;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.contact_single_row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        PersonInfo personInfo = personInfos.get(position);
        holder.textView.setText(personInfo.getPersonName());

    }

    @Override
    public int getItemCount() {
        return personInfos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.nameSEt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onClick(personInfos.get(getAdapterPosition()));
                    clickListener.onLongClick(personInfos.get(getAdapterPosition()));
                }
            });
        }
    }
    public interface ClickListener{
        void onClick(PersonInfo personInfo);
        void onLongClick(PersonInfo personInfo);
    }
}
