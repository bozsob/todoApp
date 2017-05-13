package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.TodoDaoMem;
import dao.TodoManager;
import model.Todo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/todo")
public class TodoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String textOfTodo = request.getParameter("todo");
        TodoDaoMem.getInstance().addTodo(textOfTodo);
        List<Todo> todos = TodoDaoMem.getInstance().getTodos();

        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), todos);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String filter = request.getParameter("filter");
        List<Todo> todos = TodoDaoMem.getInstance().getTodos();
        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();

        if(filter.equals("all")) {
            objectMapper.writeValue(response.getOutputStream(), todos);
        }
        if(filter.equals("inprogress")) {
            List<Todo> todosInProgress = TodoManager.getInstance().getTodosInProgress();
            objectMapper.writeValue(response.getOutputStream(), todosInProgress);
        }
        if(filter.equals("completed")) {
            List<Todo> todosCompleted = TodoManager.getInstance().getTodosDone();
            objectMapper.writeValue(response.getOutputStream(), todosCompleted);
        }




    }
}
