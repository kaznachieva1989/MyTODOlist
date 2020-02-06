package com.example.mytodolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ChangeTaskActivity extends AppCompatActivity {
    ArrayList<Task> tasks = new ArrayList<>();

    EditText title;
    EditText description;
    EditText deadline;

    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changed);
        Log.d("ololo", "onCreate : called");

        title = findViewById(R.id.task_title);
        description = findViewById(R.id.task_description);
        deadline = findViewById(R.id.task_deadline);

        getIncomingIntent();

        Button changeBtn = findViewById(R.id.task_change);
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task();

                if (title.getText().toString().trim().equals("")) {
                    showMessage("Input title please");
                    return;
                } else {
                    task.title = title.getText().toString().trim();
                }

                if (description.getText().toString().trim().equals("")) {
                    showMessage("Input description please");
                    return;
                } else {
                    task.description = description.getText().toString().trim();
                }

                task.deadline = deadline.getText().toString();
                Log.d("ololo", "Change ");
                Intent intent = new Intent();
                intent.putExtra("changedTasks", task);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    public void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void getIncomingIntent() {
        Intent intent = getIntent();
        task = (Task)intent.getSerializableExtra("tasksss");
        if (task != null) {
            title.setText(task.title);
            description.setText(task.description);
            deadline.setText(task.deadline);
        }
    }
}