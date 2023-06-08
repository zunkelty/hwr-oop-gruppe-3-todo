package hwr.oop.todo.persistence;

import hwr.oop.todo.application.AppConfig;
import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.project.ProjectFactory;
import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.tag.TagFactory;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskFactory;
import hwr.oop.todo.library.task.TaskState;

import java.sql.*;
import java.util.UUID;

public class DatabaseAdapter implements PersistenceAdapter {

    private final Connection connection;
import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.todolist.ToDoList;

public class DatabaseAdapter implements Persistence {
    @Override
    public void insertProject(Project project) {

    }

    @Override
    public void insertTag(Tag tag) {

    public DatabaseAdapter() {
        try {
            String password = AppConfig.getProperty("db.password");
            String user = AppConfig.getProperty("db.user");
            String url = AppConfig.getProperty("db.url");
            System.out.println(user);
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
            String sql = "SELECT * FROM tasks WHERE id = " + id;
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
            String sql = "UPDATE tasks SET title = " + task.getTitle() + ", description = " + task.getDescription() + ", state = " + task.getState() + " WHERE tasks.id = " + task.getId();
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException();
        }
    }

    public void insertTask(Task task) {
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO tasks (id, title, description, state) VALUES (" + task.getId() + ", " + task.getTitle() + ", " + task.getDescription() + ", " + task.getState() + ")";
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException();
        }
    }

    public void deleteTask(Task task) {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM tasks WHERE id = " + task.getId();
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
            String sql = "SELECT * FROM projects WHERE id = " + id;
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
            String sql = "UPDATE projects SET name = " + project.getName()  + " WHERE id = " + project.getId();
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException();
        }
    }

    public void insertProject(Project project) {
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO projects (id, name) VALUES (" + project.getId() + ", " + project.getName() + ")";
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException();
        }
    }

    public void deleteProject(Project project) {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM projects WHERE id = " + project.getId();
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
            String sql = "SELECT * FROM tags WHERE id = " + id;
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
            String sql = "UPDATE tags SET name = " + tag.getName() + ", description=" + tag.getDescription() + " WHERE id = " + tag.getId();
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException();
        }
    }

    public void insertTag (Tag tag){
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO tags (id, name, description) VALUES (" + tag.getId() + ", " + tag.getName() + ", " + tag.getDescription() + ")";
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException();
        }
    }

    public void deleteTag (Tag tag){
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM tags WHERE id = " + tag.getId();
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException();
        }
    }

}
