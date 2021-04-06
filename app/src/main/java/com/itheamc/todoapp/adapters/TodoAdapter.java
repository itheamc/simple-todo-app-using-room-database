package com.itheamc.todoapp.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.itheamc.todoapp.databinding.TodoViewBinding;
import com.itheamc.todoapp.models.Todo;

import static com.itheamc.todoapp.models.Todo.todoItemCallback;

public class TodoAdapter extends ListAdapter<Todo, TodoAdapter.TodoViewHolder> {

    public TodoAdapter() {
        super(todoItemCallback);
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        TodoViewBinding todoViewBinding = TodoViewBinding.inflate(inflater, parent, false);
        return new TodoViewHolder(todoViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = getItem(position);
        holder.todoViewBinding.setTodo(todo);
    }

    public static class TodoViewHolder extends RecyclerView.ViewHolder {
        private final TodoViewBinding todoViewBinding;

        public TodoViewHolder(@NonNull TodoViewBinding viewBinding) {
            super(viewBinding.getRoot());
            this.todoViewBinding = viewBinding;
        }
    }

}
