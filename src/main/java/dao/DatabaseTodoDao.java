package dao;

import model.Todo;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseTodoDao {

    private final Connection conn;
    private String query = "";

    public DatabaseTodoDao(Connection conn) {
        this.conn = conn;
    }

    public List<Todo> getTodos() throws SQLException{

        List<Todo> todos = new ArrayList<>();
        Todo todo;
        query = "SELECT * FROM todos";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            todo = new Todo(rs.getInt("id"),
                rs.getString("text"),
                rs.getBoolean("status"));
            todos.add(todo);
        }
        return todos;
    }

    public Todo getTodo(Integer id) throws SQLException {

        Todo todo = null;
        query = "SELECT FROM todos WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            todo = new Todo(rs.getInt(1),
                rs.getString(2),
                rs.getBoolean(3));
        }
        return todo;
    }

    public void addTodo(String text) throws SQLException {

        query = "INSERT INTO todos(text, status) VALUES(?,?)";
        PreparedStatement ps = conn.prepareStatement(query);

        // ps.setInt(1, );
        ps.setString(1, text);
        ps.setBoolean(2, false);
        ps.executeUpdate(query, ps.RETURN_GENERATED_KEYS);
    }

    public void toggleStatus(Integer id) throws SQLException {

        boolean status = false;

        for(Todo t : getTodos()) {
            if(t.getId().equals(id)) {
                status = t.isComplete();
                query = "UPDATE todos SET status = ? WHERE id = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setBoolean(1, !status);
                ps.setInt(2, id);
                ps.executeUpdate();
            }
        }
    }

    public void deleteTodo(Integer id) throws SQLException {

        for(Todo t : getTodos()) {
            if(t.getId().equals(id)) {
                query = "DELETE FROM todos WHERE id = ?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, id);
                ps.executeUpdate();
            }
        }
    }
}
