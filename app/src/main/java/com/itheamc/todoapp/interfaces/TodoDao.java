package com.itheamc.todoapp.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.itheamc.todoapp.models.Todo;

import java.util.List;

@Dao
public interface TodoDao {
    @Query("SELECT * FROM todo")
    LiveData<List<Todo>> getAllTodo();

    @Query("SELECT * FROM todo WHERE _id LIKE :id")
    Todo findById(int id);

    @Insert
    void insertTodo(Todo todo);

    @Delete
    void deleteToto(Todo todo);

}
