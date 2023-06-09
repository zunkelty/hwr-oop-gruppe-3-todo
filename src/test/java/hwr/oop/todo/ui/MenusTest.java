package hwr.oop.todo.ui;

import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.project.ProjectFactory;
import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.tag.TagFactory;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskFactory;
import hwr.oop.todo.library.todolist.NotFoundException;
import hwr.oop.todo.library.todolist.ToDoList;
import hwr.oop.todo.ui.menu.Menu;
import hwr.oop.todo.ui.menu.MenuAction;
import hwr.oop.todo.ui.menu.responses.MenuResponse;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MenusTest {

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

    private final List<Menu> requiredSubmenus = Arrays.asList(Menus.TAG, Menus.TASK, Menus.PROJECT, Menus.INTRAY );

    @Test
    void AllSubmenusAreReachableFromHome() {
        ToDoList toDoList = new ToDoList();
        ParameterProvider parameterProvider = new SequentialInputsParameterProvider();

        List<Menu> navigationTargets = Menus.HOME.getActions()
                .stream()
                .map(menuAction -> menuAction.run(toDoList, parameterProvider).navigationTarget())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());


        assertTrue(navigationTargets.containsAll(requiredSubmenus));
    }

    @Test
    void AllSubmenusCanGoBackToHome(){
        ToDoList toDoList = new ToDoList();
        ParameterProvider parameterProvider = new SequentialInputsParameterProvider();

        Menus.HOME.getActions()
                .stream()
                .map(menuAction -> menuAction.run(toDoList, parameterProvider).navigationTarget())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(menu -> {
                    MenuAction backAction = menu.getActions().get(menu.getActions().size() - 1);
                    MenuResponse response = backAction.run(toDoList, parameterProvider);

                    assertTrue(response.navigationTarget().isPresent());
                    assertEquals(Menus.HOME, response.navigationTarget().get());
                });
    }

    @Test
    void CanCreateTask(){
        ToDoList toDoList = new ToDoList();
        ParameterProvider parameterProvider = new SequentialInputsParameterProvider("Test Task");

        MenuResponse response = Menus.TASK.handle('a', toDoList, parameterProvider);

        assertTrue(response.isSuccess());
    }

    @Test
    void CanGetTask(){
        ToDoList toDoList = new ToDoList();

        Task task = TaskFactory.createTask("Test Task");
        toDoList.addTask(task);

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(task.getId().toString());

        MenuResponse response = Menus.TASK.handle('b', toDoList, parameterProvider);

        assertTrue(response.isSuccess());

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
        ToDoList toDoList = new ToDoList();
        ParameterProvider parameterProvider = new SequentialInputsParameterProvider("Test InTrayTask");

        MenuResponse response = Menus.INTRAY.handle('a', toDoList, parameterProvider);

        assertTrue(response.isSuccess());
    }

    @Test
    void CanGetInTrayTask() {
        ToDoList toDoList = new ToDoList();

        Task task = TaskFactory.createTask("Test InTrayTask");
        toDoList.addInTrayTask(task);

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(task.getId().toString());

        MenuResponse response = Menus.INTRAY.handle('b', toDoList, parameterProvider);

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
        ToDoList toDoList = new ToDoList();

        Task task = TaskFactory.createTask("Test InTrayTask");
        toDoList.addInTrayTask(task);

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(task.getId().toString());

        MenuResponse response = Menus.INTRAY.handle('c', toDoList, parameterProvider);

        assertTrue(response.isSuccess());

        assertThrows(NotFoundException.class, () -> toDoList.getInTrayTask(task.getId()));


    }

    @Test
    void CanMoveInTrayTask(){
        ToDoList toDoList = new ToDoList();

        Task task = TaskFactory.createTask("Test InTrayTask");
        toDoList.addInTrayTask(task);

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(task.getId().toString());

        MenuResponse response = Menus.INTRAY.handle('d', toDoList, parameterProvider);

        assertTrue(response.isSuccess());

        assertThrows(NotFoundException.class, () -> toDoList.getInTrayTask(task.getId()));
        assertEquals(task, toDoList.getTask(task.getId()));


    }
    @Test
    void CanCreateTag(){
        ToDoList toDoList = new ToDoList();
        ParameterProvider parameterProvider = new SequentialInputsParameterProvider("Test Tag");

        MenuResponse response = Menus.TAG.handle('a', toDoList, parameterProvider);

        assertTrue(response.isSuccess());
    }

    @Test
    void CanGetTag(){
        ToDoList toDoList = new ToDoList();

        Tag tag = TagFactory.createTag("Tag Name");
        toDoList.createTag(tag);

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(tag.getId().toString());

        MenuResponse response = Menus.TAG.handle('b', toDoList, parameterProvider);

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
        ToDoList toDoList = new ToDoList();
        ParameterProvider parameterProvider = new SequentialInputsParameterProvider("Test Project");

        MenuResponse response = Menus.PROJECT.handle('a', toDoList, parameterProvider);

        assertTrue(response.isSuccess());
    }

    @Test
    void CanGetProject(){
        ToDoList toDoList = new ToDoList();

        Project project = ProjectFactory.createProject("Test Project");
        toDoList.createProject(project);

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(project.getId().toString());

        MenuResponse response = Menus.PROJECT.handle('b', toDoList, parameterProvider);

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
        ToDoList toDoList = new ToDoList();

        Project project = ProjectFactory.createProject("Test Project");
        toDoList.createProject(project);

        Task task = TaskFactory.createTask("Test Task");
        toDoList.addTask(task);

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(project.getId().toString(), task.getId().toString());

        MenuResponse response = Menus.PROJECT.handle('c', toDoList, parameterProvider);

        assertTrue(response.isSuccess());
    }

    @Test
    void CanAddTagToTask(){
        ToDoList toDoList = new ToDoList();

        Tag tag = TagFactory.createTag("Tag Name");
        toDoList.createTag(tag);

        Task task = TaskFactory.createTask("Test Task");
        toDoList.addTask(task);

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(tag.getId().toString(), task.getId().toString());

        MenuResponse response = Menus.TASK.handle('c', toDoList, parameterProvider);

        assertTrue(response.isSuccess());
    }

    @Test
    void CanGetTasksOfProject(){
        ToDoList toDoList = new ToDoList();

        Project project = ProjectFactory.createProject("Test Project");
        toDoList.createProject(project);

        Task task = TaskFactory.createTask("Test Task");
        toDoList.addTask(task);

        project.addTask(task);

        ParameterProvider parameterProvider = new SequentialInputsParameterProvider(project.getId().toString());

        MenuResponse response = Menus.PROJECT.handle('d', toDoList, parameterProvider);

        HashMap<String, String> expectedTable = new HashMap<>(Map.of(
                "Projekt", "Test Project",
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
