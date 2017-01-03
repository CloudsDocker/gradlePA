package com.todzhang.todo.repository;

import com.sun.tools.javac.comp.Todo;
import com.todzhang.todo.model.ToDoItem;

import java.util.List;

/**
 * Created by todzhang on 2017/1/2.
 */
public interface ToDoRepository {
    List<ToDoItem> findAll();
    ToDoItem findById(Long id);
    Long insert(ToDoItem item);
    void update(ToDoItem item);
    void delete(ToDoItem item);
}

