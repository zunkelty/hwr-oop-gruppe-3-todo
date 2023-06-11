package hwr.oop.todo.ui;

import hwr.oop.todo.application.usecases.GetInTrayTaskUseCase;
import hwr.oop.todo.application.usecases.UseCases;
import hwr.oop.todo.cli.ui.Menus;
import hwr.oop.todo.cli.ui.ParameterProvider;
import hwr.oop.todo.cli.ui.menu.responses.Table;
import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.project.ProjectFactory;
import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.tag.TagFactory;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskFactory;
import hwr.oop.todo.library.task.TaskState;
import hwr.oop.todo.library.todolist.NotFoundException;
import hwr.oop.todo.library.todolist.ToDoList;
import hwr.oop.todo.cli.ui.menu.Menu;
import hwr.oop.todo.cli.ui.menu.MenuAction;
import hwr.oop.todo.cli.ui.menu.responses.MenuResponse;
import hwr.oop.todo.persistence.Persistence;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MenusTest {
    Persistence mockPersistence = new Persistence() {
        @Override
        public void deleteInTrayTask(UUID id) {

        }

        @Override
        public void createInTrayTask(Task task) {

        }

        @Override
        public void createProject(Project project) {

        }

        @Override
        public void createTag(Tag tag) {

        }

        @Override
        public void createTask(Task task) {

        }

        @Override
        public ToDoList readToDoList() {
            return new ToDoList();
        }

        @Override
        public void updateProject(Project project) {

        }

        @Override
        public void updateTask(Task task) {

        }
    };

    private static class SequentialInputsParameterProvider implements ParameterProvider {
        private final List<String> inputs = new ArrayList<>();

        public SequentialInputsParameterProvider(String... inputs) {
            this.inputs.addAll(Arrays.asList(inputs));
        }

        @Override
        public String getRequiredParameter(String name) {
            String parameter = inputs.get(0);
            inputs.remove(0);
            return parameter;
        }

        @Override
        public Optional<String> getOptionalParameter(String name) {
            return Optional.empty();
        }
    }

    private final List<Menu> requiredSubmenus = Arrays.asList(Menus.TAG, Menus.TASK, Menus.PROJECT);

    @Test
    void AllSubmenusAreReachableFromHome() {
        List<Menu> navigationTargets = Menus.HOME.getActions()
                .stream()
                .map(menuAction -> menuAction.run(null, null).navigationTarget())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());


        assertTrue(navigationTargets.containsAll(requiredSubmenus));
    }

    @Test
    void AllSubmenusCanGoBackToHome() {
        Menus.HOME.getActions()
                .stream()
                .map(menuAction -> menuAction.run(null, null).navigationTarget())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(menu -> {
                    MenuAction backAction = menu.getActions().get(menu.getActions().size() - 1);
                    MenuResponse response = backAction.run(null, null);

                    assertTrue(response.navigationTarget().isPresent());
                    assertEquals(Menus.HOME, response.navigationTarget().get());
                });
    }

    @Test
    void CanCreateTask() {
        UseCases useCases = UseCases.initialize(mockPersistence);
        ParameterProvider parameterProvider = new SequentialInputsParameterProvider("Test Task");

        MenuResponse response = Menus.TASK.handle('a', useCases, parameterProvider);

        assertTrue(response.isSuccess());
    }

    @Test
    void CanGetTask() {
        UseCases useCases = UseCases.initialize(mockPersistence);

        Task task = TaskFactory.createTask("Test Task");
        useCases.getCreateTaskUseCase().insertTask(task);

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(task.getId().toString());

        MenuResponse response = Menus.TASK.handle('b', useCases, parameterProvider);

        assertTrue(response.isSuccess());
        assertNotNull(task);

        Table expectedTable = new Table()
                .withRow("ID", task.getId().toString())
                .withRow("Titel", "Test Task")
                .withRow("Beschreibung", "")
                .withRow("Status", "Nicht begonnen")
                .withRow("Tags", "");

        assertTrue(response.table().isPresent());
        Table table = response.table().get();

        assertEquals(expectedTable, table);
    }

    @Test
    void CanCreateInTrayTask() {
        UseCases useCases = UseCases.initialize(mockPersistence);
        ParameterProvider parameterProvider = new SequentialInputsParameterProvider("Test Task");

        MenuResponse response = Menus.INTRAY.handle('a', useCases, parameterProvider);

        assertTrue(response.isSuccess());
    }

    @Test
    void CanGetInTrayTask() {
        UseCases useCases = UseCases.initialize(mockPersistence);
        Task task = TaskFactory.createTask("Test InTrayTask");
        useCases.getCreateInTrayTaskUseCase().insertInTrayTask(task);

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(task.getId().toString());

        MenuResponse response = Menus.INTRAY.handle('b', useCases, parameterProvider);

        Table expectedTable = new Table()
                .withRow("ID", task.getId().toString())
                .withRow("Titel", "Test InTrayTask")
                .withRow("Beschreibung", "")
                .withRow("Tags", "");

        assertTrue(response.table().isPresent());
        Table table = response.table().get();

        assertEquals(expectedTable, table);
    }

    @Test
    void CanDeleteInTrayTask() {
        UseCases useCases = UseCases.initialize(mockPersistence);

        Task task = TaskFactory.createTask("Test InTrayTask");
        useCases.getCreateInTrayTaskUseCase().insertInTrayTask(task);

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(task.getId().toString());

        MenuResponse response = Menus.INTRAY.handle('c', useCases, parameterProvider);

        assertTrue(response.isSuccess());

        UUID taskId = task.getId();
        GetInTrayTaskUseCase useCase = useCases.getInTrayTaskUseCase();
        assertThrows(NotFoundException.class, () -> useCase.getInTrayTaskById(taskId));
    }

    @Test
    void CanMoveInTrayTask() {
        UseCases useCases = UseCases.initialize(mockPersistence);

        Task task = TaskFactory.createTask("Test InTrayTask");
        useCases.getCreateInTrayTaskUseCase().insertInTrayTask(task);

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(task.getId().toString());

        MenuResponse response = Menus.INTRAY.handle('d', useCases, parameterProvider);

        assertTrue(response.isSuccess());

        UUID taskId = task.getId();
        GetInTrayTaskUseCase useCase = useCases.getInTrayTaskUseCase();
        assertThrows(NotFoundException.class, () -> useCase.getInTrayTaskById(taskId));
        assertEquals(task, useCases.getTaskUseCase().getTaskById(task.getId()));
    }

    @Test
    void CanCreateTag() {
        UseCases useCases = UseCases.initialize(mockPersistence);
        ParameterProvider parameterProvider = new SequentialInputsParameterProvider("Test Tag");

        MenuResponse response = Menus.TAG.handle('a', useCases, parameterProvider);

        assertTrue(response.isSuccess());
    }

    @Test
    void CanGetTag() {
        UseCases useCases = UseCases.initialize(mockPersistence);

        Tag tag = TagFactory.createTag("Tag Name");
        useCases.getCreateTagUseCase().insertTag(tag);

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(tag.getId().toString());

        MenuResponse response = Menus.TAG.handle('b', useCases, parameterProvider);

        Table expectedTable = new Table()
                .withRow("ID", tag.getId().toString())
                .withRow("Name", "Tag Name")
                .withRow("Beschreibung", "");

        assertTrue(response.table().isPresent());
        Table table = response.table().get();

        assertEquals(expectedTable, table);
    }

    @Test
    void CanCreateProject() {
        UseCases useCases = UseCases.initialize(mockPersistence);
        ParameterProvider parameterProvider = new SequentialInputsParameterProvider("Test Project");

        MenuResponse response = Menus.PROJECT.handle('a', useCases, parameterProvider);

        assertTrue(response.isSuccess());
    }

    @Test
    void CanGetProject() {
        UseCases useCases = UseCases.initialize(mockPersistence);

        Project project = ProjectFactory.createProject("Test Project");
        useCases.getCreateProjectUseCase().createProject(project);

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(project.getId().toString());

        MenuResponse response = Menus.PROJECT.handle('b', useCases, parameterProvider);

        Table expectedTable = new Table()
                .withRow("ID", project.getId().toString())
                .withRow("Name", "Test Project")
                .withRow("Zugeordnete Aufgaben", "0");

        assertTrue(response.table().isPresent());
        Table table = response.table().get();

        assertEquals(expectedTable, table);
    }

    @Test
    void CanAddTaskToProject() {
        UseCases useCases = UseCases.initialize(mockPersistence);

        Project project = ProjectFactory.createProject("Test Project");
        useCases.getCreateProjectUseCase().createProject(project);

        Task task = TaskFactory.createTask("Test Task");
        useCases.getCreateTaskUseCase().insertTask(task);

        useCases.getAddTaskToProjectUseCase().addTaskToProject(project.getId(), task.getId());

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(project.getId().toString(), task.getId().toString());
        MenuResponse response = Menus.PROJECT.handle('c', useCases, parameterProvider);

        assertTrue(response.isSuccess());
    }

    @Test
    void CanAddTagToTask() {
        UseCases useCases = UseCases.initialize(mockPersistence);

        Tag tag = TagFactory.createTag("Tag Name");
        useCases.getCreateTagUseCase().insertTag(tag);

        Task task = TaskFactory.createTask("Test Task");
        useCases.getCreateTaskUseCase().insertTask(task);

        useCases.getAddTagToTaskUseCase().addTagToTask(task.getId(), tag.getId());

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(tag.getId().toString(), task.getId().toString());
        MenuResponse response = Menus.TASK.handle('c', useCases, parameterProvider);

        assertTrue(response.isSuccess());
    }

    @Test
    void CanGetTasksOfProject() {
        UseCases useCases = UseCases.initialize(mockPersistence);

        Project project = ProjectFactory.createProject("Test Project");
        useCases.getCreateProjectUseCase().createProject(project);

        Task task = TaskFactory.createTask("Test Task");
        useCases.getCreateTaskUseCase().insertTask(task);

        useCases.getAddTaskToProjectUseCase().addTaskToProject(project.getId(), task.getId());

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(project.getId().toString());

        MenuResponse response = Menus.PROJECT.handle('d', useCases, parameterProvider);

        Table expectedTable = new Table()
                .withRow("ID", task.getId().toString())
                .withRow("Titel", "Test Task")
                .withRow("Beschreibung", "")
                .withRow("Tags", "")
                .withDividerRow();

        Table responseTable = response.table().get();

        assertTrue(response.table().isPresent());
        assertEquals(expectedTable, responseTable);
    }

    @Test
    void canGetOpenTasks() {
        UseCases useCases = UseCases.initialize(mockPersistence);

        Task openTask = new Task(UUID.randomUUID(), "Open Task", "", TaskState.OPEN);
        Task inProgressTask = new Task(UUID.randomUUID(), "In Progress Task", "", TaskState.IN_PROGRESS);
        Task doneTask = new Task(UUID.randomUUID(), "Done Task", "", TaskState.DONE);

        useCases.getCreateTaskUseCase().insertTask(openTask);
        useCases.getCreateTaskUseCase().insertTask(inProgressTask);
        useCases.getCreateTaskUseCase().insertTask(doneTask);

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider();

        MenuResponse response = Menus.TASK.handle('d', useCases, parameterProvider);

        Table expectedTable = new Table()
                .withRow("ID", openTask.getId().toString())
                .withRow("Titel", "Open Task")
                .withRow("Beschreibung", "")
                .withRow("Tags", "")
                .withRow("Status", "Nicht begonnen")
                .withDividerRow()
                .withRow("ID", inProgressTask.getId().toString())
                .withRow("Titel", "In Progress Task")
                .withRow("Beschreibung", "")
                .withRow("Tags", "")
                .withRow("Status", "In Bearbeitung")
                .withDividerRow();

        assertTrue(response.table().isPresent());
        List<Table.Row> rows = response.table().get().getRows();
        List<Table.Row> expectedRows = expectedTable.getRows();

        // The table returned by the menu is not sorted, so the order of elements can vary
        // To compare the tables, we can't use `equals`
        assertTrue(rows.containsAll(expectedRows));
    }

    @Test
    void canUpdateTask() {
        UseCases useCases = UseCases.initialize(mockPersistence);

        Task task = TaskFactory.createTask("Test Task");
        useCases.getCreateTaskUseCase().insertTask(task);

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(task.getId().toString());

        MenuResponse response = Menus.TASK.handle('e', useCases, parameterProvider);

        assertTrue(response.isSuccess());
        assertNotNull(task);
    }

    @Test
    void canDisplayTaskAfterCompletion() {
        UseCases useCases = UseCases.initialize(mockPersistence);

        Task task = TaskFactory.createTask("Test Task");
        useCases.getCreateTaskUseCase().insertTask(task);

        task.setState(TaskState.DONE);
        useCases.getUpdateTaskUseCase().updateTask(task);

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(task.getId().toString());

        MenuResponse response = Menus.TASK.handle('b', useCases, parameterProvider);

        assertTrue(response.isSuccess());

        Table table = new Table()
                .withRow("ID", task.getId().toString())
                .withRow("Titel", "Test Task")
                .withRow("Beschreibung", "")
                .withRow("Status", "Erledigt")
                .withRow("Tags", "");

        assertTrue(response.table().isPresent());
        assertEquals(table, response.table().get());
    }
}

