package hwr.oop.todo.persistence;

import hwr.oop.todo.application.AppConfig;
import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.project.ProjectFactory;
import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.tag.TagFactory;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskFactory;
import hwr.oop.todo.library.task.TaskState;
import hwr.oop.todo.library.todolist.ToDoList;

import java.sql.*;
import java.util.UUID;


public class DatabaseAdapter implements Persistence {

    private final Connection connection;

    public DatabaseAdapter() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            String password = AppConfig.getProperty("db.password");
            String user = AppConfig.getProperty("db.username");
            String url = AppConfig.getProperty("db.url");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new FailedDatabaseConnectionException();
        }
    }

    //Task methods
    //TODO: also add tags

    public Task getTask(UUID id) {
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("SELECT * FROM Tasks WHERE id = '%s'", id);
            ResultSet resultSet = statement.executeQuery(sql);
            Task task = null;
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                TaskState state = TaskState.valueOf(resultSet.getString("state"));
                task = TaskFactory.createTask(state, id, title, description);
            }
            resultSet.close();
            statement.close();
            return task;
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException();
        }
    }
    public void updateTask(Task task) {
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("UPDATE Tasks SET title = '%s', description = '%s', state = '%s' WHERE id = '%s'", task.getTitle(), task.getDescription(), task.getState(), task.getId());
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException();
        }
    }

    public void insertTask(Task task) {
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("INSERT INTO Tasks (id, title, description, state) VALUES ('%s', '%s', '%s', '%s')", task.getId(), task.getTitle(), task.getDescription(), task.getState());
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException();
        }
    }

    public void deleteTask(Task task) {
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("DELETE FROM Tasks WHERE id = '%s'", task.getId());
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException();
        }
    }

    //Project methods
    // TODO: implement project tags list

    public Project getProject(UUID id) {
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("SELECT * FROM Projects WHERE id = '%s'", id);
            ResultSet resultSet = statement.executeQuery(sql);
            Project project = null;
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                project = ProjectFactory.createProject(id, name);
            }
            resultSet.close();
            statement.close();
            return project;
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException();
        }
    }

    public void updateProject(Project project) {
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("UPDATE Projects SET name = '%s' WHERE id = '%s'", project.getName(), project.getId());
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException();
        }
    }

    public void insertProject(Project project) {
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("INSERT INTO Projects (id, name) VALUES ('%s', '%s')", project.getId(), project.getName());
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException();
        }
    }

    public void deleteProject(Project project) {
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("DELETE FROM Projects WHERE id = '%s'", project.getId());
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException();
        }
    }

    //Tag methods

    public Tag getTag (UUID id){
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("SELECT * FROM Tags WHERE id = '%s'", id);
            ResultSet resultSet = statement.executeQuery(sql);
            Tag tag = null;
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                tag = TagFactory.createTag(id, name, description);
            }
            resultSet.close();
            statement.close();
            return tag;
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException();
        }
    }

    public void updateTag (Tag tag){
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("UPDATE Tags SET name = '%s', description = '%s' WHERE id = '%s'", tag.getName(), tag.getDescription(), tag.getId());
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException();
        }
    }

    public void insertTag (Tag tag){
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("INSERT INTO Tags (id, name, description) VALUES ('%s', '%s', '%s')", tag.getId(), tag.getName(), tag.getDescription());
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException();
        }
    }

    public void deleteTag (Tag tag){
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("DELETE FROM Tags WHERE id = '%s'", tag.getId());
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException();
        }
    }

    @Override
    public ToDoList loadToDoList() {
        return new ToDoList();
    }
}
