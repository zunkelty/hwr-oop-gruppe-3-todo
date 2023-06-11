package hwr.oop.todo.cli.ui;

import hwr.oop.todo.application.usecases.UseCases;
import hwr.oop.todo.cli.ui.menu.Menu;
import hwr.oop.todo.cli.ui.menu.responses.MenuResponse;
import hwr.oop.todo.cli.ui.menu.responses.StringResponse;
import hwr.oop.todo.cli.ui.menu.responses.TableResponse;
import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.project.ProjectFactory;
import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.tag.TagFactory;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskFactory;
import hwr.oop.todo.library.task.TaskState;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class Menus {

    private static final String BACK = "Zurück";

    private static final String TITLE = "Titel";

    private static final String DESC = "Beschreibung";

    private static final String STATE = taskStateToString(TaskState.OPEN);

    public static final Menu TASK = new Menu()
            .on('a', "Aufgabe anlegen").execute(Menus::createTask)
            .on('b', "Aufgabe anzeigen (mit ID)").execute(Menus::getTask)
            .on('c', "Tag zu Aufgabe hinzufügen").execute(Menus::addTagToTask)
            .on('d', "Alle offenen Aufgaben anzeigen").execute(Menus::showOpenTasks)
            .on('e', "Aufgabe bearbeiten").execute(Menus::updateTask)
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
            .on('d', "Eingangsliste").navigateTo(() -> Menus.INTRAY)
            .on('q', "Beenden").quit();

    private static String taskStateToString(TaskState taskState){
        if(taskState == TaskState.OPEN) return "Nicht begonnen";
        if(taskState == TaskState.IN_PROGRESS) return "In Bearbeitung";
        if(taskState == TaskState.DONE) return "Erledigt";
        return "Unbekannt";
    }

    private static TaskState stringToTaskState(String taskState){
        if(taskState == "Nicht begonnen") return TaskState.OPEN;
        if(taskState == "In Bearbeitung") return TaskState.IN_PROGRESS;
        if(taskState == "Erledigt") return TaskState.DONE;
        return TaskState.OPEN;
    }

    private static MenuResponse updateTask(UseCases useCases, ParameterProvider parameter) {
        String sId = parameter.getRequiredParameter("ID");
        Optional<String> title = parameter.getOptionalParameter(TITLE);
        Optional<String> description = parameter.getOptionalParameter(DESC);
        Optional<String> state = parameter.getOptionalParameter(STATE);
        UUID id = UUID.fromString(sId);
        Task task = useCases.getTaskUseCase().getTaskById(id);
        task.setTitle(title.orElse(task.getTitle()));
        task.setDescription(description.orElse(task.getDescription()));
        task.setState(stringToTaskState(state.orElse(taskStateToString(task.getState()))));
        useCases.getUpdateTaskUseCase().updateTask(task);

        return StringResponse.with("Aufgabe wurde bearbeitet (ID: " + task.getId() + ")");
    }

    private static StringResponse createTask(UseCases useCases, ParameterProvider parameters) {
        String title = parameters.getRequiredParameter(TITLE);
        Optional<String> description = parameters.getOptionalParameter(DESC);

        Task task = description.map(desc -> TaskFactory.createTask(title, desc)).orElseGet(() -> TaskFactory.createTask(title));
        useCases.getCreateTaskUseCase().insertTask(task);

        return StringResponse.with("Aufgabe wurde angelegt (ID: " + task.getId() + ")");
    }

    private static TableResponse getTask(UseCases useCases, ParameterProvider parameters) {
        String sId = parameters.getRequiredParameter("ID");
        UUID id = UUID.fromString(sId);

        Task task = useCases.getTaskUseCase().getTaskById(id);

        return new TableResponse()
                .withRow("ID", task.getId().toString())
                .withRow(TITLE, task.getTitle())
                .withRow(DESC, task.getDescription())
                .withRow("Status", taskStateToString(task.getState()))
                .withRow("Tags", task.getTags().stream().map(Tag::getName).collect(Collectors.joining(",")));
    }

    private static TableResponse showOpenTasks(UseCases useCases, ParameterProvider parameterProvider){
        List<Task> openTasks = useCases.getOpenTasksUseCase().getOpenTasks();

        TableResponse response = new TableResponse();

        for (Task task : openTasks) {
            response.withRow("ID", task.getId().toString())
                    .withRow(TITLE, task.getTitle())
                    .withRow(DESC, task.getDescription())
                    .withRow("Tags", task.getTags().stream().map(Tag::getName).collect(Collectors.joining(",")))
                    .withRow("---", "---");
        }

        return response;
    }

    private static StringResponse createInTrayTask(UseCases useCases, ParameterProvider parameters) {
        String title = parameters.getRequiredParameter(TITLE);
        Optional<String> description = parameters.getOptionalParameter(DESC);

        Task task = description.map(desc -> TaskFactory.createTask(title, desc)).orElseGet(() -> TaskFactory.createTask(title));
        useCases.getCreateInTrayTaskUseCase().insertInTrayTask(task);

        return StringResponse.with("Aufgabe wurde angelegt (ID: " + task.getId() + ")");
    }

    private static TableResponse getInTrayTask(UseCases useCases, ParameterProvider parameters) {
        String sId = parameters.getRequiredParameter("ID");
        UUID id = UUID.fromString(sId);

        Task task = useCases.getInTrayTaskUseCase().getInTrayTaskById(id);

        return new TableResponse()
                .withRow("ID", task.getId().toString())
                .withRow(TITLE, task.getTitle())
                .withRow(DESC, task.getDescription())
                .withRow("Tags", task.getTags().stream().map(Tag::getName).collect(Collectors.joining(",")));
    }

    private static StringResponse deleteInTrayTask(UseCases useCases, ParameterProvider parameters) {
        String sId = parameters.getRequiredParameter("ID");
        UUID id = UUID.fromString(sId);

        Task task = useCases.getInTrayTaskUseCase().getInTrayTaskById(id);

        useCases.deleteInTrayTaskUseCase().deleteInTrayTask(id);

        return StringResponse.with("Aufgabe " + task.getTitle() + " wurde entfernt");
    }

    private static StringResponse moveInTrayTask(UseCases useCases, ParameterProvider parameterProvider) {
        String sTaskId = parameterProvider.getRequiredParameter("Aufgaben ID");

        UUID taskId = UUID.fromString(sTaskId);

        Task task = useCases.getInTrayTaskUseCase().getInTrayTaskById(taskId);

        useCases.deleteInTrayTaskUseCase().deleteInTrayTask(taskId);

        useCases.getCreateTaskUseCase().insertTask(task);

        return StringResponse.with("Aufgabe wurde in die To-Do-Liste verschoben (ID: " + task.getId() + ")");
    }

    private static StringResponse createProject(UseCases useCases, ParameterProvider parameters){
        String name = parameters.getRequiredParameter("Name");

        Project project = ProjectFactory.createProject(name);
        useCases.getCreateProjectUseCase().createProject(project);

        return StringResponse.with("Projekt wurde angelegt (ID: " + project.getId() + ")");
    }

    private static TableResponse getProject(UseCases useCases, ParameterProvider parameters) {
        String sId = parameters.getRequiredParameter("ID");
        UUID id = UUID.fromString(sId);

        Project project = useCases.getProjectUseCase().getProjectById(id);

        return new TableResponse()
                .withRow("ID", project.getId().toString())
                .withRow("Name", project.getName())
                .withRow("Zugeordnete Aufgaben", String.valueOf(project.getTasks().size()));
    }

    private static StringResponse addTaskToProject(UseCases useCases, ParameterProvider parameterProvider){
        String sProjectId = parameterProvider.getRequiredParameter("Projekt ID");
        String sTaskId = parameterProvider.getRequiredParameter("Aufgaben ID");

        UUID projectId = UUID.fromString(sProjectId);
        UUID taskId = UUID.fromString(sTaskId);

        useCases.getAddTaskToProjectUseCase().addTaskToProject(projectId, taskId);

        return StringResponse.with("Aufgabe wurde zum Projekt hinzugefügt");
    }

    private static TableResponse getTasksOfProject(UseCases useCases, ParameterProvider parameterProvider){
        String sProjectId = parameterProvider.getRequiredParameter("Projekt ID");

        UUID projectId = UUID.fromString(sProjectId);

        List<Task> tasks = useCases.getTasksFromProjectUseCase().getTasksOfProject(projectId);

        TableResponse response = new TableResponse();

        for (Task task : tasks) {
            response.withRow("ID", task.getId().toString())
                    .withRow(TITLE, task.getTitle())
                    .withRow(DESC, task.getDescription())
                    .withRow("Tags", task.getTags().stream().map(Tag::getName).collect(Collectors.joining(",")))
                    .withRow("---", "---");
        }

        return response;
    }

    private static StringResponse createTag(UseCases useCases, ParameterProvider parameters){
        String name = parameters.getRequiredParameter("Name");
        String description = parameters.getOptionalParameter(DESC).orElse("");

        Tag tag = TagFactory.createTag(name, description);
        useCases.getCreateTagUseCase().insertTag(tag);

        return StringResponse.with("Tag wurde angelegt (ID: " + tag.getId() + ")");
    }

    private static TableResponse getTag(UseCases useCases, ParameterProvider parameters) {
        String sId = parameters.getRequiredParameter("ID");
        UUID id = UUID.fromString(sId);

        Tag tag = useCases.getTagUseCase().getTagById(id);

        return new TableResponse()
                .withRow("ID", tag.getId().toString())
                .withRow("Name", tag.getName())
                .withRow(DESC, tag.getDescription());
    }

    private static StringResponse addTagToTask(UseCases useCases, ParameterProvider parameterProvider){
        String sTagId = parameterProvider.getRequiredParameter("Tag ID");
        String sTaskId = parameterProvider.getRequiredParameter("Aufgaben ID");

        UUID tagId = UUID.fromString(sTagId);
        UUID taskId = UUID.fromString(sTaskId);

        useCases.getAddTagToTaskUseCase().addTagToTask(taskId, tagId);

        return StringResponse.with("Tag wurde zur Aufgabe hinzugefügt");
    }

    private Menus() {

    }
}
