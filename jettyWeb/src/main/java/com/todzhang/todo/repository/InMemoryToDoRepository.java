package com.todzhang.todo.repository;

import com.todzhang.todo.model.ToDoItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by todzhang on 2017/1/2.
 */
public class InMemoryToDoRepository implements ToDoRepository{

    private AtomicLong currentId=new AtomicLong();
    private ConcurrentMap<Long,ToDoItem> toDos=new ConcurrentHashMap<>();

    @Override
    public List<ToDoItem> findAll() {
        List<ToDoItem> toDoItems=new ArrayList<>(toDos.values());
        Collections.sort(toDoItems);
        return toDoItems;
    }

    @Override
    public ToDoItem findById(Long id) {
        return toDos.get(id);
    }

    @Override
    public Long insert(ToDoItem item) {
       Long newID=currentId.incrementAndGet();
        item.setId(newID);
        toDos.putIfAbsent(newID,item);
        return newID;
    }

    @Override
    public void update(ToDoItem item) {
        toDos.replace(item.getId(),item);

    }

    @Override
    public void delete(ToDoItem item) {
        toDos.remove(item.getId());

    }
}
