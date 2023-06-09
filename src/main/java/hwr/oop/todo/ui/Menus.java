package hwr.oop.todo.ui;

import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.project.ProjectFactory;
import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.tag.TagFactory;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskFactory;
import hwr.oop.todo.library.todolist.ToDoList;
import hwr.oop.todo.ui.menu.Menu;
import hwr.oop.todo.ui.menu.responses.MenuResponse;
import hwr.oop.todo.ui.menu.responses.TableResponse;
import hwr.oop.todo.ui.menu.responses.StringResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class Menus {

    private static final String BACK = "Zurück";

    private static final String TITLE = "Titel";

    private static final String DESC = "Beschreibung";

    public static final Menu TASK = new Menu()
            .on('a', "Aufgabe anlegen").execute(Menus::createTask)
            .on('b', "Aufgabe anzeigen (mit ID)").execute(Menus::getTask)
            .on('c', "Tag zu Aufgabe hinzufügen").execute(Menus::addTagToTask)
            .on('z', BACK).navigateTo(() -> Menus.HOME);

    public static final Menu PROJECT = new Menu()
            .on('a', "Projekt anlegen").execute(Menus::createProject)
            .on('b', "Projekt anzeigen (mit ID)").execute(Menus::getProject)
            .on('c', "Aufgabe zu Projekt hinzufügen").execute(Menus::addTaskToProject)
            .on('d', "Alle Aufgaben eines Projekts anzeigen").execute(Menus::getTasksOfProject)
            .on('z', BACK).navigateTo(() -> Menus.HOME);

    public static final Menu TAG = new Menu()
            .on('a', "Tag anlegen").execute(Menus::createTag)
            .on('b', "Tag anzeigen (mit ID)").execute(Menus::getTag)
            .on('z', BACK).navigateTo(() -> Menus.HOME);

    public static final Menu INTRAY = new Menu()
            .on('a', "Aufgabe anlegen").execute(Menus::createInTrayTask)
            .on('b', "Aufgabe anzeigen (mit ID)").execute(Menus::getInTrayTask)
            .on('c', "Aufgabe löschen").execute(Menus::deleteInTrayTask)
            .on('d', "Aufgabe in die To-Do-Liste verschieben").execute(Menus::moveInTrayTask)
            .on('z', BACK).navigateTo(() -> Menus.HOME);

    public static final Menu HOME = new Menu()
            .on('a', "Tasks anzeigen/bearbeiten").navigateTo(() -> Menus.TASK)
            .on('b', "Projekte anzeigen/bearbeiten").navigateTo(() -> Menus.PROJECT)
            .on('c', "Tags anzeigen/bearbeiten").navigateTo(() -> Menus.TAG)
            .on('d', "Eingangsliste").navigateTo(() -> Menus.INTRAY);

    private static StringResponse createTask(ToDoList toDoList, ParameterProvider parameters) {
        String title = parameters.getRequiredParameter(TITLE);
        Optional<String> description = parameters.getOptionalParameter(DESC);

        Task task = description.map(desc -> TaskFactory.createTask(title, desc)).orElseGet(() -> TaskFactory.createTask(title));
        toDoList.addTask(task);

        return StringResponse.with("Aufgabe wurde angelegt (ID: " + task.getId() + ")");
    }

    private static TableResponse getTask(ToDoList toDoList, ParameterProvider parameters) {
        String sId = parameters.getRequiredParameter("ID");
        UUID id = UUID.fromString(sId);

        Task task = toDoList.getTask(id);

        return new TableResponse()
                .withRow("ID", task.getId().toString())
                .withRow(TITLE, task.getTitle())
                .withRow(DESC, task.getDescription())
                .withRow("Tags", task.getTags().stream().map(Tag::getName).collect(Collectors.joining(",")));
    }

    private static StringResponse createInTrayTask(ToDoList toDoList, ParameterProvider parameters) {
        String title = parameters.getRequiredParameter(TITLE);
        Optional<String> description = parameters.getOptionalParameter(DESC);

        Task task = description.map(desc -> TaskFactory.createTask(title, desc)).orElseGet(() -> TaskFactory.createTask(title));
        toDoList.addInTrayTask(task);

        return StringResponse.with("Aufgabe wurde angelegt (ID: " + task.getId() + ")");
    }

    private static TableResponse getInTrayTask(ToDoList toDoList, ParameterProvider parameters) {
        String sId = parameters.getRequiredParameter("ID");
        UUID id = UUID.fromString(sId);

        Task task = toDoList.getInTrayTask(id);

        return new TableResponse()
                .withRow("ID", task.getId().toString())
                .withRow(TITLE, task.getTitle())
                .withRow(DESC, task.getDescription())
                .withRow("Tags", task.getTags().stream().map(Tag::getName).collect(Collectors.joining(",")));
    }

    private static StringResponse deleteInTrayTask(ToDoList toDoList, ParameterProvider parameters) {
        String sId = parameters.getRequiredParameter("ID");
        UUID id = UUID.fromString(sId);

        Task task = toDoList.getInTrayTask(id);

        toDoList.deleteInTrayTask(id);

        return StringResponse.with("Aufgabe " + task.getTitle() + " wurde entfernt");
    }

    private static MenuResponse moveInTrayTask(ToDoList toDoList, ParameterProvider parameterProvider) {
        String sTaskId = parameterProvider.getRequiredParameter("Aufgaben ID");

        UUID taskId = UUID.fromString(sTaskId);

        Task task = toDoList.getInTrayTask(taskId);
        toDoList.deleteInTrayTask(taskId);

        toDoList.addTask(task);

        return StringResponse.with("Aufgabe wurde in die To-Do-Liste verschoben (ID: " + task.getId() + ")");
    }


    private static StringResponse createProject(ToDoList toDoList, ParameterProvider parameters){
        String name = parameters.getRequiredParameter("Name");

        Project project = ProjectFactory.createProject(name);
        toDoList.createProject(project);

        return StringResponse.with("Projekt wurde angelegt (ID: " + project.getId() + ")");
    }

    private static TableResponse getProject(ToDoList toDoList, ParameterProvider parameters) {
        String sId = parameters.getRequiredParameter("ID");
        UUID id = UUID.fromString(sId);

        Project project = toDoList.getProject(id);

        return new TableResponse()
                .withRow("ID", project.getId().toString())
                .withRow("Name", project.getName())
                .withRow("Zugeordnete Aufgaben", String.valueOf(project.getTasks().size()));
    }

    private static StringResponse addTaskToProject(ToDoList toDoList, ParameterProvider parameterProvider){
        String sProjectId = parameterProvider.getRequiredParameter("Projekt ID");
        String sTaskId = parameterProvider.getRequiredParameter("Aufgaben ID");

        UUID projectId = UUID.fromString(sProjectId);
        UUID taskId = UUID.fromString(sTaskId);

        Project project = toDoList.getProject(projectId);
        Task task = toDoList.getTask(taskId);

        project.addTask(task);

        return StringResponse.with("Aufgabe wurde zum Projekt hinzugefügt");
    }

    private static TableResponse getTasksOfProject(ToDoList toDoList, ParameterProvider parameterProvider){
        String sProjectId = parameterProvider.getRequiredParameter("Projekt ID");

        UUID projectId = UUID.fromString(sProjectId);

        Project project = toDoList.getProject(projectId);
        List<Task> tasks = project.getTasks();

        TableResponse response = new TableResponse();
        response.withRow("Projekt", project.getName());

        for (Task task : tasks) {
            response.withRow("ID", task.getId().toString())
                    .withRow(TITLE, task.getTitle())
                    .withRow(DESC, task.getDescription())
                    .withRow("Tags", task.getTags().stream().map(Tag::getName).collect(Collectors.joining(",")))
                    .withRow("---", "---");
        }

        return response;
    }

    private static StringResponse createTag(ToDoList toDoList, ParameterProvider parameters){
        String name = parameters.getRequiredParameter("Name");
        String description = parameters.getOptionalParameter(DESC).orElse("");

        Tag tag = TagFactory.createTag(name, description);
        toDoList.createTag(tag);

        return StringResponse.with("Tag wurde angelegt (ID: " + tag.getId() + ")");
    }

    private static TableResponse getTag(ToDoList toDoList, ParameterProvider parameters) {
        String sId = parameters.getRequiredParameter("ID");
        UUID id = UUID.fromString(sId);

        Tag tag = toDoList.getTag(id);

        return new TableResponse()
                .withRow("ID", tag.getId().toString())
                .withRow("Name", tag.getName())
                .withRow(DESC, tag.getDescription());
    }

    private static StringResponse addTagToTask(ToDoList toDoList, ParameterProvider parameterProvider){
        String sTagId = parameterProvider.getRequiredParameter("Tag ID");
        String sTaskId = parameterProvider.getRequiredParameter("Aufgaben ID");

        UUID tagId = UUID.fromString(sTagId);
        UUID taskId = UUID.fromString(sTaskId);

        Tag tag = toDoList.getTag(tagId);
        Task task = toDoList.getTask(taskId);

        task.addTag(tag);

        return StringResponse.with("Tag wurde zur Aufgabe hinzugefügt");
    }

    private Menus() {

    }
}
