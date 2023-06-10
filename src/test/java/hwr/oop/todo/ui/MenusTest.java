package hwr.oop.todo.ui;

import hwr.oop.todo.application.usecases.UseCases;
import hwr.oop.todo.cli.ui.Menus;
import hwr.oop.todo.cli.ui.ParameterProvider;
import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.project.ProjectFactory;
import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.tag.TagFactory;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskFactory;
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
        public void insertProject(Project project) {

        }

        @Override
        public void insertTag(Tag tag) {

        }

        @Override
        public void insertTask(Task task) {

        }

        @Override
        public ToDoList loadToDoList() {
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
    void AllSubmenusCanGoBackToHome(){
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
    void CanCreateTask(){
        UseCases useCases = UseCases.initialize(mockPersistence);
        ParameterProvider parameterProvider = new SequentialInputsParameterProvider("Test Task");

        MenuResponse response = Menus.TASK.handle('a', useCases, parameterProvider);

        assertTrue(response.isSuccess());
    }

    @Test
    void CanGetTask(){
        UseCases useCases = UseCases.initialize(mockPersistence);

        Task task = TaskFactory.createTask("Test Task");
        useCases.getCreateTaskUseCase().insertTask(task);

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(task.getId().toString());

        MenuResponse response = Menus.TASK.handle('b', useCases, parameterProvider);

        assertTrue(response.isSuccess());
        assertNotNull(task);

        HashMap<String, String> expectedTable = new HashMap<>(Map.of(
                "Titel", "Test Task",
                "ID", task.getId().toString(),
                "Beschreibung", "",
                "Tags", ""
        ));

        assertTrue(response.table().isPresent());
        LinkedHashMap<String, String> responseTable = response.table().get();

        expectedTable.keySet().forEach(key -> assertEquals(expectedTable.get(key), responseTable.get(key)));
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

        assertTrue(response.isSuccess());

        HashMap<String, String> expectedTable = new HashMap<>(Map.of(
                "Titel", "Test InTrayTask",
                "ID", task.getId().toString(),
                "Beschreibung", "",
                "Tags", ""
        ));

        assertTrue(response.table().isPresent());
        LinkedHashMap<String, String> responseTable = response.table().get();

        expectedTable.keySet().forEach(key -> assertEquals(expectedTable.get(key), responseTable.get(key)));
    }
    @Test
    void CanDeleteInTrayTask(){
        UseCases useCases = UseCases.initialize(mockPersistence);

        Task task = TaskFactory.createTask("Test InTrayTask");
        useCases.getCreateInTrayTaskUseCase().insertInTrayTask(task);

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(task.getId().toString());

        MenuResponse response = Menus.INTRAY.handle('c', useCases, parameterProvider);

        assertTrue(response.isSuccess());

        assertThrows(NotFoundException.class, () -> useCases.getInTrayTaskUseCase().getInTrayTaskById(task.getId()));


    }

    @Test
    void CanMoveInTrayTask(){
        UseCases useCases = UseCases.initialize(mockPersistence);

        Task task = TaskFactory.createTask("Test InTrayTask");
        useCases.getCreateInTrayTaskUseCase().insertInTrayTask(task);

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(task.getId().toString());

        MenuResponse response = Menus.INTRAY.handle('d', useCases, parameterProvider);

        assertTrue(response.isSuccess());

        assertThrows(NotFoundException.class, () -> useCases.getInTrayTaskUseCase().getInTrayTaskById(task.getId()));
        assertEquals(task, useCases.getTaskUseCase().getTaskById(task.getId()));


    }

    @Test
    void CanCreateTag(){
        UseCases useCases = UseCases.initialize(mockPersistence);
        ParameterProvider parameterProvider = new SequentialInputsParameterProvider("Test Tag");

        MenuResponse response = Menus.TAG.handle('a', useCases, parameterProvider);

        assertTrue(response.isSuccess());
    }

    @Test
    void CanGetTag(){
        UseCases useCases = UseCases.initialize(mockPersistence);

        Tag tag = TagFactory.createTag("Tag Name");
        useCases.getCreateTagUseCase().insertTag(tag);

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(tag.getId().toString());

        MenuResponse response = Menus.TAG.handle('b', useCases, parameterProvider);

        HashMap<String, String> expectedTable = new HashMap<>(Map.of(
                "Name", "Tag Name",
                "ID", tag.getId().toString(),
                "Beschreibung", ""
        ));

        assertTrue(response.table().isPresent());
        LinkedHashMap<String, String> responseTable = response.table().get();

        expectedTable.keySet().forEach(key -> assertEquals(expectedTable.get(key), responseTable.get(key)));

        assertTrue(response.isSuccess());
    }

    @Test
    void CanCreateProject(){
        UseCases useCases = UseCases.initialize(mockPersistence);
        ParameterProvider parameterProvider = new SequentialInputsParameterProvider("Test Project");

        MenuResponse response = Menus.PROJECT.handle('a', useCases, parameterProvider);

        assertTrue(response.isSuccess());
    }

    @Test
    void CanGetProject(){
        UseCases useCases = UseCases.initialize(mockPersistence);

        Project project = ProjectFactory.createProject("Test Project");
        useCases.getCreateProjectUseCase().createProject(project);

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(project.getId().toString());

        MenuResponse response = Menus.PROJECT.handle('b', useCases, parameterProvider);

        HashMap<String, String> expectedTable = new HashMap<>(Map.of(
                "Name", "Test Project",
                "ID", project.getId().toString(),
                "Zugeordnete Aufgaben", "0"
        ));

        assertTrue(response.table().isPresent());
        LinkedHashMap<String, String> responseTable = response.table().get();

        expectedTable.keySet().forEach(key -> assertEquals(expectedTable.get(key), responseTable.get(key)));

        assertTrue(response.isSuccess());
    }

    @Test
    void CanAddTaskToProject(){
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
    void CanAddTagToTask(){
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
    void CanGetTasksOfProject(){
        UseCases useCases = UseCases.initialize(mockPersistence);

        Project project = ProjectFactory.createProject("Test Project");
        useCases.getCreateProjectUseCase().createProject(project);

        Task task = TaskFactory.createTask("Test Task");
        useCases.getCreateTaskUseCase().insertTask(task);

        useCases.getAddTaskToProjectUseCase().addTaskToProject(project.getId(), task.getId());

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(project.getId().toString());

        MenuResponse response = Menus.PROJECT.handle('d', useCases, parameterProvider);

        HashMap<String, String> expectedTable = new HashMap<>(Map.of(
                "ID", task.getId().toString(),
                "Titel", "Test Task",
                "Beschreibung", "",
                "Tags", "",
                "---", "---"
        ));

        assertTrue(response.table().isPresent());
        LinkedHashMap<String, String> responseTable = response.table().get();

        expectedTable.keySet().forEach(key -> assertEquals(expectedTable.get(key), responseTable.get(key)));

        assertTrue(response.isSuccess());
    }
}
