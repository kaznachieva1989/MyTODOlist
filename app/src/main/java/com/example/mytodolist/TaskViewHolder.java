package com.example.mytodolist;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskViewHolder extends RecyclerView.ViewHolder implements ItemClickListener, View.OnClickListener {

    TextView title;
    TextView description;
    TextView deadline;

    ItemClickListener itemClickListener;

    public TaskViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);

        title=itemView.findViewById(R.id.vh_title);
        description=itemView.findViewById(R.id.vh_description);
        deadline=itemView.findViewById(R.id.vh_deadline);
        this.itemClickListener=itemClickListener;

        itemView.setOnClickListener(this);
    }

    public void onBind(Task task){
        title.setText(task.title);
        description.setText(task.description);
        deadline.setText(task.deadline);
    }

    @Override
    public void onItemClick(int position) {
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onItemClick(getAdapterPosition());
    }
}
