package hwr.oop.todo.ui.menu;

import hwr.oop.todo.application.usecases.UseCases;
import hwr.oop.todo.cli.IO;
import hwr.oop.todo.cli.ui.MenuController;
import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.todolist.ToDoList;
import hwr.oop.todo.persistence.Persistence;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MenuControllerTest {

    private final Persistence persistence = new Persistence() {
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
        public void deleteInTrayTask(UUID id) {

        }

        @Override
        public ToDoList readToDoList() {
            return null;
        }

        @Override
        public void updateProject(Project project) {

        }

        @Override
        public void updateTask(Task task) {

        }
    };

    @Test
    void canBeQuitWithQ() {
        ByteArrayInputStream in = new ByteArrayInputStream("q".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        IO io = new IO(in, out);
        UseCases useCases = UseCases.initialize(persistence);
        MenuController controller = new MenuController(useCases, io);

        controller.execute();

        String output = out.toString();
        assertTrue(output.contains("Auf Wiedersehen!"));
    }

    @Test
    void repeatsUntilQuit() {
        ByteArrayInputStream in = new ByteArrayInputStream("a\nz\nq".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        IO io = new IO(in, out);
        UseCases useCases = UseCases.initialize(persistence);
        MenuController controller = new MenuController(useCases, io);

        controller.execute();

        String output = out.toString();
        assertTrue(output.contains("Auf Wiedersehen!"));
    }

    @Test
    void printsDefaultErrorMessage() {
        ByteArrayInputStream in = new ByteArrayInputStream("a\n8\nz\nq".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        IO io = new IO(in, out);
        UseCases useCases = UseCases.initialize(persistence);
        MenuController controller = new MenuController(useCases, io);

        controller.execute();

        String output = out.toString();
        assertTrue(output.contains("Das hat leider nicht geklappt..."));
        assertTrue(output.contains("Auf Wiedersehen!"));
    }
}
