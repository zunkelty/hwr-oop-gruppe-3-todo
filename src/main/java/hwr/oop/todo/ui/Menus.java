package hwr.oop.todo.ui;

import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskFactory;
import hwr.oop.todo.ui.menu.Menu;
import hwr.oop.todo.ui.menu.responses.TableResponse;
import hwr.oop.todo.ui.menu.responses.StringResponse;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class Menus {

    public static final Menu TASK = new Menu()
            .on('a', "Aufgabe anlegen").execute((toDoList, parameters) -> {
                String title = parameters.getRequiredParameter("Titel");
                Optional<String> description = parameters.getOptionalParameter("Beschreibung");

                Task task = description.map(desc -> TaskFactory.createTask(title, desc)).orElseGet(() -> TaskFactory.createTask(title));
                toDoList.addTask(task);

                return StringResponse.with("Successfully created task: "+task.getId());
            })
            .on('b', "Aufgabe anzeigen (nach ID)").execute((toDoList, parameters) -> {
                String sId = parameters.getRequiredParameter("ID");
                UUID id = UUID.fromString(sId);

                Task task = toDoList.getTask(id);

                return new TableResponse()
                        .withRow("ID", task.getId().toString())
                        .withRow("Titel", task.getTitle())
                        .withRow("Beschreibung", task.getDescription())
                        .withRow("Tags", task.getTags().stream().map(Tag::getName).collect(Collectors.joining(",")));
            }).on('z', "Zurück").navigateTo(() -> Menus.HOME);

    public static final Menu PROJECT = new Menu()
            .on('a', "Projekt anlegen").execute((toDoList, parameterProvider) -> StringResponse.with(""))
            .on('z', "Zurück").navigateTo(() -> Menus.HOME);

    public static final Menu HOME = new Menu()
            .on('a', "Tasks anzeigen/bearbeiten").navigateTo(() -> Menus.TASK)
            .on('b', "Projekte anzeigen/bearbeiten").navigateTo(() -> Menus.PROJECT);

    private Menus() {

    }
}
