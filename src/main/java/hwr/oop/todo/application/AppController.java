package hwr.oop.todo.application;

import hwr.oop.todo.application.usecases.UseCases;
import hwr.oop.todo.cli.CLI;
import hwr.oop.todo.persistence.DatabaseAdapter;

public class AppController {
    public static void main(String[] args){
        DatabaseAdapter db = new DatabaseAdapter();

        UseCases useCases = UseCases.initialize(db);

        @SuppressWarnings("java:S106")
        CLI cli = new CLI(System.in, System.out, useCases);

        cli.start();
    }

}
