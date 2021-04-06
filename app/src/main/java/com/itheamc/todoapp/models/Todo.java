package com.itheamc.todoapp.models;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.Objects;

@Entity
public class Todo {
    @PrimaryKey(autoGenerate = true)
    private int _id;

    @ColumnInfo(name = "title")
    private String _title;

    @ColumnInfo(name = "desc")
    private String _desc;

    @ColumnInfo(name = "date")
    private String _added_on;

    // Constructor
    public Todo() {
    }

    public Todo(String _title, String _desc, String _added_on) {
        this._title = _title;
        this._desc = _desc;
        this._added_on = _added_on;
    }

    // Getters and Setters

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_desc() {
        return _desc;
    }

    public void set_desc(String _desc) {
        this._desc = _desc;
    }

    public String  get_added_on() {
        return _added_on;
    }

    public void set_added_on(String  _added_on) {
        this._added_on = _added_on;
    }


    // ToString()

    @Override
    public String toString() {
        return "Todo{" +
                "_id=" + _id +
                ", _title='" + _title + '\'' +
                ", _desc='" + _desc + '\'' +
                ", _added_on='" + _added_on + '\'' +
                '}';
    }


    // Overriding equals() method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return get_id() == todo.get_id() &&
                get_title().equals(todo.get_title()) &&
                get_desc().equals(todo.get_desc()) &&
                get_added_on().equals(todo.get_added_on());
    }


    // DiffUtil Callback
    public static DiffUtil.ItemCallback<Todo> todoItemCallback = new DiffUtil.ItemCallback<Todo>() {
        @Override
        public boolean areItemsTheSame(@NonNull Todo oldItem, @NonNull Todo newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Todo oldItem, @NonNull Todo newItem) {
            return oldItem.get_id() == newItem.get_id() &&
                    oldItem.get_title().equals(newItem.get_title()) &&
                    oldItem.get_desc().equals(newItem.get_desc()) &&
                    oldItem.get_added_on().equals(newItem.get_added_on());
        }
    };

}
