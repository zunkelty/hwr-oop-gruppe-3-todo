package hwr.oop.todo.cli;

import hwr.oop.todo.application.usecases.UseCases;
import hwr.oop.todo.cli.ui.MenuController;

import java.io.InputStream;
import java.io.OutputStream;

public class CLI {
    private final MenuController menuController;

    public CLI(InputStream in, OutputStream out, UseCases useCases){
        IO io = new IO(in, out);
        menuController = new MenuController(useCases, io);
    }

    public void start(){
        menuController.execute();
    }

}
