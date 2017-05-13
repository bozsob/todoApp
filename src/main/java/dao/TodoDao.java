package dao;

import model.Todo;

import java.util.List;

public interface TodoDao {

    List<Todo> getTodos();
    Todo getTodo(Integer id);
    Todo addTodo(String text);
    void toggleStatus(Integer id);
    void deleteTodo(Integer id);
}
