package com.itheamc.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.itheamc.todoapp.adapters.TodoAdapter;
import com.itheamc.todoapp.databinding.ActivityMainBinding;
import com.itheamc.todoapp.db.TodoDatabase;
import com.itheamc.todoapp.interfaces.TodoDao;
import com.itheamc.todoapp.models.Todo;
import com.itheamc.todoapp.viewmodels.TodoViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding mainBinding;
    private TodoViewModel todoViewModel;
    private TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        todoAdapter = new TodoAdapter();
        mainBinding.recyclerView.setAdapter(todoAdapter);

        todoViewModel = new ViewModelProvider(new ViewModelStore(), new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(TodoViewModel.class);
        todoViewModel.getTodos().observe(this, todos -> {
            Log.d(TAG, "onCreate: " + todos.toString());
            todoAdapter.submitList(todos);
        });

        mainBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mainBinding.title.getText().toString().trim();
                String desc = mainBinding.desc.getText().toString().trim();
                if (!title.isEmpty() && !desc.isEmpty()) {
                    Todo todo = new Todo(
                            title,
                            desc,
                            new Date().toString()
                    );
                    mainBinding.title.setText("");
                    mainBinding.desc.setText("");
                    hideKeyboard();
                    todoViewModel.insertTodo(todo);

                } else {
                    Toast.makeText(MainActivity.this, "Please Enter the data", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    // Function to hide the keyboard
    private void hideKeyboard() {

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

}