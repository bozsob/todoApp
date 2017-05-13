package dao;

import model.Todo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by trixi on 2017.05.10..
 */
public class TodoDaoMem implements TodoDao {

    private static TodoDaoMem todoDaoMem = new TodoDaoMem();
    private TodoDaoMem() {}
    public static TodoDaoMem getInstance() {
        return todoDaoMem;
    }

    //list is working as a database
    private List<Todo> todos = new ArrayList<>();
    private Integer idCounter = 1;

    @Override
    public List<Todo> getTodos() {
        return todos;
    }

    @Override
    public Todo getTodo(Integer id) {

        Todo result = null;
        for(Todo t : todos) {
            if(t.getId().equals(id)) {
                result = t;
            }
        }
        return result;
    }

    @Override
    public Todo addTodo(String text) {

        if (todos.size() == 0) {
            Todo todo = new Todo(idCounter, text, false);
            todos.add(todo);
            return todo;
        }
        else {
            int maxId = idCounter;
            for(int i = 0; i < todos.size(); i++) {
                if (todos.get(i).getId() >= maxId) {
                    maxId = todos.get(i).getId() + 1;
                }
            }
            Todo todo = new Todo(maxId, text, false);
            todos.add(todo);
            return todo;
        }
    }

    @Override
    public void toggleStatus(Integer id) {

        for(Todo t : todos) {
            if(t.getId().equals(id)) {
                if(t.isComplete()) {
                    t.setComplete(false);
                }
                else t.setComplete(true);
            }
        }
    }

    @Override
    public void deleteTodo(Integer id) {

        Iterator<Todo> iter = todos.iterator();
        while(iter.hasNext()) {
            Todo t = iter.next();
            if(t.getId().equals(id)) {
                iter.remove();
            }
        }
    }
}
