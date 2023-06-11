package hwr.oop.todo.persistence;

import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.tag.TagFactory;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskState;
import hwr.oop.todo.library.todolist.ToDoList;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class DatabaseAdapter implements Persistence {

    private final Connection connection;

    public DatabaseAdapter(String connectionUrl) {
        try {
            connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            throw new FailedDatabaseConnectionException(e);
        }
    }

    public DatabaseAdapter(Connection connection){
        this.connection = connection;
    }


    public void updateTask(Task task) {
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("UPDATE Tasks SET title = '%s', description = '%s', state = '%s' WHERE id = '%s'", task.getTitle(), task.getDescription(), task.getState(), task.getId());
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException(e);
        }
    }

    public void createTask(Task task) {
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("INSERT INTO Tasks (id, title, description, state) VALUES ('%s', '%s', '%s', '%s')", task.getId(), task.getTitle(), task.getDescription(), task.getState());
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException(e);
        }
    }

    public void deleteTask(Task task) {
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("DELETE FROM Tasks WHERE id = '%s'", task.getId());
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException(e);
        }
    }

    public Project getProject(UUID id) {
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("SELECT * FROM Projects WHERE id = '%s'", id);
            ResultSet resultSet = statement.executeQuery(sql);
            Project project = null;
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                project = new Project(id, name);
            }
            resultSet.close();
            statement.close();
            return project;
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException(e);
        }
    }

    public void updateProject(Project project) {
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("UPDATE Projects SET name = '%s' WHERE id = '%s'", project.getName(), project.getId());
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException(e);
        }
    }

    public void createProject(Project project) {
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("INSERT INTO Projects (id, name) VALUES ('%s', '%s')", project.getId(), project.getName());
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException(e);
        }
    }

    public void deleteProject(Project project) {
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("DELETE FROM Projects WHERE id = '%s'", project.getId());
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException(e);
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
            throw new FailedDatabaseStatementException(e);
        }
    }

    public void updateTag (Tag tag){
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("UPDATE Tags SET name = '%s', description = '%s' WHERE id = '%s'", tag.getName(), tag.getDescription(), tag.getId());
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException(e);
        }
    }

    public void createTag(Tag tag){
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("INSERT INTO Tags (id, name, description) VALUES ('%s', '%s', '%s')", tag.getId(), tag.getName(), tag.getDescription());
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException(e);
        }
    }

    public void deleteTag (Tag tag){
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("DELETE FROM Tags WHERE id = '%s'", tag.getId());
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException(e);
        }
    }

    private List<Tag> loadTagsFromDatabase(){
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Tags";

            ResultSet resultSet = statement.executeQuery(sql);
            List<Tag> tags = new ArrayList<>();

            while (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("id"));
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                tags.add(new Tag(id, name, description));
            }

            resultSet.close();
            statement.close();

            return tags;
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException(e);
        }
    }

    private List<Project> loadProjectsFromDatabase(){
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Projects";

            ResultSet resultSet = statement.executeQuery(sql);
            List<Project> projects = new ArrayList<>();

            while (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("id"));
                String name = resultSet.getString("name");
                projects.add(new Project(id, name));
            }

            resultSet.close();
            statement.close();

            return projects;
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException(e);
        }
    }

    private List<Task> loadTasksFromDatabase(){
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Tasks";

            ResultSet resultSet = statement.executeQuery(sql);
            List<Task> tasks = new ArrayList<>();

            while (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("id"));
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                TaskState state = TaskState.valueOf(resultSet.getString("state"));
                tasks.add(new Task(id, title, description, state));
            }

            resultSet.close();
            statement.close();

            return tasks;
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException(e);
        }
    }

    private List<Task> loadInTrayTasksFromDatabase(){
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM InTrayTasks";

            ResultSet resultSet = statement.executeQuery(sql);
            List<Task> tasks = new ArrayList<>();

            while (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("id"));
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                TaskState state = TaskState.valueOf(resultSet.getString("state"));
                tasks.add(new Task(id, title, description, state));
            }

            resultSet.close();
            statement.close();

            return tasks;
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException(e);
        }
    }

    private HashMap<UUID, UUID> getTasksOfAllProject(){
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Projects_Tasks";

            ResultSet resultSet = statement.executeQuery(sql);
            HashMap<UUID, UUID> tasksOfProjects = new HashMap<>();

            while (resultSet.next()) {
                UUID taskId = UUID.fromString(resultSet.getString("task_id"));
                UUID projectId = UUID.fromString(resultSet.getString("project_id"));
                tasksOfProjects.put(projectId, taskId);
            }

            resultSet.close();
            statement.close();

            return tasksOfProjects;
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException(e);
        }
    }

    private HashMap<UUID, UUID> getTagsOfAllTasks(){
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM Tags_Tasks";

            ResultSet resultSet = statement.executeQuery(sql);
            HashMap<UUID, UUID> tagsOfTasks = new HashMap<>();

            while (resultSet.next()) {
                UUID taskId = UUID.fromString(resultSet.getString("task_id"));
                UUID tagId = UUID.fromString(resultSet.getString("tag_id"));
                tagsOfTasks.put(taskId, tagId);
            }

            resultSet.close();
            statement.close();

            return tagsOfTasks;
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException(e);
        }
    }

    @Override
    public ToDoList readToDoList() {
        ToDoList toDoList = new ToDoList();

        List<Tag> tags = loadTagsFromDatabase();
        List<Project> projects = loadProjectsFromDatabase();
        List<Task> tasks = loadTasksFromDatabase();
        List<Task> inTrayTasks = loadInTrayTasksFromDatabase();

        // Create the entities in the toDoList
        tags.forEach(toDoList::createTag);
        projects.forEach(toDoList::createProject);
        tasks.forEach(toDoList::createTask);
        inTrayTasks.forEach(toDoList::createInTrayTask);

        // Add the tasks to the projects
        HashMap<UUID, UUID> tasksOfProjects = getTasksOfAllProject();
        tasksOfProjects.forEach((projectId, taskId) -> {
            Project project = toDoList.getProject(projectId);
            Task task = toDoList.getTask(taskId);
            project.addTask(task);
        });

        // Add the tags to the tasks
        HashMap<UUID, UUID> tagsOfTasks = getTagsOfAllTasks();
        tagsOfTasks.forEach((taskId, tagId) -> {
            Task task = toDoList.getTask(taskId);
            Tag tag = toDoList.getTag(tagId);
            task.addTag(tag);
        });

        return toDoList;
    }

    @Override
    public void createInTrayTask(Task task) {
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("INSERT INTO InTrayTasks (id, title, description, state) VALUES ('%s', '%s', '%s', '%s')", task.getId(), task.getTitle(), task.getDescription(), task.getState());
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException(e);
        }
    }

    @Override
    public void deleteInTrayTask(UUID id) {
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("DELETE FROM InTrayTasks WHERE id = '%s'", id);
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException(e);
        }
    }
}
