package com.itheamc.todoapp.repositories;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.itheamc.todoapp.db.TodoDatabase;
import com.itheamc.todoapp.interfaces.TodoDao;
import com.itheamc.todoapp.models.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TodoRepository {
    private LiveData<List<Todo>> liveData;
    private TodoDatabase todoDatabase;
    private TodoDao todoDao;

    public TodoRepository(Application application) {
        todoDatabase = TodoDatabase.getDatabase(application.getApplicationContext());
        todoDao = todoDatabase.todoDao();
        if (liveData == null) {
            liveData = new MutableLiveData<List<Todo>>();
            liveData = (LiveData<List<Todo>>) todoDao.getAllTodo();
        }
    }


    // Function to return the all todos
    public LiveData<List<Todo>> getAllTodos() {
        return liveData;
    }


    // Function to insert todo
    public void insertTodo(Todo todo) {
        TodoDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                todoDao.insertTodo(todo);
            }
        });
    }
}
