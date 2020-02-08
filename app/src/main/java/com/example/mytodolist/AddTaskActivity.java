package com.example.mytodolist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Task task = new Task();
    EditText title;
    EditText description;
    EditText deadline;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        title = findViewById(R.id.task_title);
        description = findViewById(R.id.task_description);
        deadline = findViewById(R.id.task_deadline);

        findViewById(R.id.task_deadline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        Button saveBtn = findViewById(R.id.task_save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (title.getText().toString().trim().equals("")) {
                    showMessage("Input title please");
                    return;
                } else {
                    task.title = title.getText().toString();
                }
                if (description.getText().toString().trim().equals("")) {
                    showMessage("Input description please");
                    return;
                } else {
                    task.description = description.getText().toString();
                }
                if (deadline.getText().toString().trim().equals("")) {
                    showMessage("Input deadline please");
                    return;
                } else {
                    task.deadline = deadline.getText().toString();
                }


                Intent intent = new Intent();
                intent.putExtra("task", task);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }

    public void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date = month + "/" + dayOfMonth + "/" + year;
        deadline.setText(date);
    }
}
