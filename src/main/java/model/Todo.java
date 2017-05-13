package model;

/**
 * Created by trixi on 2017.05.11..
 */
public class Todo {

    private Integer id;
    private String text;
    private boolean complete;

    public Todo(Integer id, String text, boolean complete) {
        this.id = id;
        this.text = text;
        this.complete = complete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return text;
    }
}
