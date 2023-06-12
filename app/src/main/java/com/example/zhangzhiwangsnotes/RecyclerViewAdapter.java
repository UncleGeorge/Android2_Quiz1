package com.example.zhangzhiwangsnotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter <RecyclerViewAdapter.ViewHolder> {

    private Context context;
    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    // onCreateViewHolder() method
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // view instance
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note, parent,false);
        return new ViewHolder(view);
    }

    // onBindViewHolder() method
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.title.setText("Note " + (position + 1));
        holder.description.setText("Description " + (position + 1));
        holder.date.setText("Jun 7, 2023");
    }

    // getItemCount() method
    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // initialize profileLetter,name,number
        TextView title, description, date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);
        }
    }
}
