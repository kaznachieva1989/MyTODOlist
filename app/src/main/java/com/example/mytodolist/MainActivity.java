package com.example.mytodolist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    ArrayList<Task> tasks = new ArrayList<>();
    TaskAdapter adapter;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Task> savedTasks = Storage.read(this);
        tasks = savedTasks;

        adapter = new TaskAdapter(tasks, this);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);

        Button button = findViewById(R.id.open);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivityForResult(intent, 42);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                tasks.remove(viewHolder.getAdapterPosition());
                adapter.notifyDataSetChanged();
                Storage.save(tasks, MainActivity.this);
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode== 42){
            Task task = (Task) data.getSerializableExtra("task");
            tasks.add(task);
            adapter.notifyDataSetChanged();
            Storage.save(tasks, MainActivity.this);
        }
        if (resultCode == RESULT_OK && requestCode == 43) {
            Task task = (Task) data.getSerializableExtra("changedTasks");
            tasks.remove(tasks.get(position));
            tasks.add(task);
            adapter.notifyDataSetChanged();
            Storage.save(tasks, this);
        }
    }

    @Override
    public void onItemClick(int position) {
        Log.d("ololo", "onItemClick: clicked" + position);
        Intent intent = new Intent(this, ChangeTaskActivity.class);
        intent.putExtra("tasksss", tasks.get(position));
        startActivityForResult(intent, 43);
    }
}
