package com.itheamc.todoapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.itheamc.todoapp.interfaces.TodoDao;
import com.itheamc.todoapp.models.Todo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Todo.class}, version = 1)
public abstract class TodoDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();

    private static volatile TodoDatabase instance;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static TodoDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (TodoDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            TodoDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return instance;
    }
}
