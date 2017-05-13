package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.TodoDaoMem;
import model.Todo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Created by trixi on 2017.05.11..
 */
@WebServlet("/todoUpdate")
public class TodoDelAndDoneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        List<Todo> tos = TodoDaoMem.getInstance().getTodos();
        for(Todo t : tos) {
            System.out.println("status of t before toggle: " + t.isComplete());
        }

        String idString = request.getParameter("id");
        int id = Integer.valueOf(idString);
        System.out.println("idInt of done: " + id);

        TodoDaoMem.getInstance().toggleStatus(id);
        List<Todo> todos = TodoDaoMem.getInstance().getTodos();
        for(Todo t : todos) {
            System.out.println("status of t: " + t.isComplete());
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("application/json");
        ObjectMapper objectMapper = new ObjectMapper();

        String idString = request.getParameter("id");
        int id = Integer.valueOf(idString);
        System.out.println("idInt of del: " + id);

        TodoDaoMem.getInstance().deleteTodo(id);

        List<Todo> todos = TodoDaoMem.getInstance().getTodos();

        for(Todo t : todos) {
            System.out.println(t);
        }

        objectMapper.writeValue(response.getOutputStream(), todos);
    }
}
