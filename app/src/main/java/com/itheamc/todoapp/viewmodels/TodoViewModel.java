package com.itheamc.todoapp.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.itheamc.todoapp.models.Todo;
import com.itheamc.todoapp.repositories.TodoRepository;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {
    private final LiveData<List<Todo>> todos;
    private final TodoRepository todoRepository;

    public TodoViewModel(Application application) {
        super(application);
        todoRepository = new TodoRepository(application);
        todos = todoRepository.getAllTodos();
    }


    // Getter of the todos
    public LiveData<List<Todo>> getTodos() {
        return todos;
    }

    // Insert Todo
    public void insertTodo(Todo todo) {
        todoRepository.insertTodo(todo);
    }
}
