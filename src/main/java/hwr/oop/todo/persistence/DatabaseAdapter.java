package hwr.oop.todo.persistence;

public class DatabaseAdapter implements PersistenceAdapter {
    private final String url = "jdbc:postgresql://localhost:5432/todo";
    private final String user = "postgres";
    private final String password = "postgres";

    public void updateTask(int id) {
    }

    public void updateProject(int id) {
    }

}
