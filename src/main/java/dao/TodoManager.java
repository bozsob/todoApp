package dao;

import model.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trixi on 2017.05.11..
 */
public class TodoManager {

    List<Todo> todos = TodoDaoMem.getInstance().getTodos();

    private static TodoManager todoManager = new TodoManager();
    private TodoManager() {}
    public static TodoManager getInstance() {
        return todoManager;
    }



    public List<Todo> getTodosInProgress() {

        List<Todo> todosInprogress = new ArrayList<>();
        for(Todo t : todos) {
            if (!t.isComplete()) {
                todosInprogress.add(t);
            }
        }
        return todosInprogress;
    }

    public List<Todo> getTodosDone() {

        List<Todo> todosDone = new ArrayList<>();
        for(Todo t : todos) {
            if (!t.isComplete()) {
                todosDone.add(t);
            }
        }
        return todosDone;
    }
}
