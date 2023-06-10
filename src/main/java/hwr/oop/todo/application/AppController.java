package hwr.oop.todo.application;

import hwr.oop.todo.application.usecases.UseCases;
import hwr.oop.todo.cli.CLI;
import hwr.oop.todo.persistence.DatabaseAdapter;

import java.util.NoSuchElementException;

public class AppController {
    public static void main(String[] args){
        String dbUrl = AppConfig.getProperty("db.url");
        System.out.println(dbUrl);

        if(dbUrl == null) throw new NoSuchElementException("Could not load properties file");

        DatabaseAdapter db = new DatabaseAdapter(dbUrl);

        UseCases useCases = UseCases.initialize(db);

        @SuppressWarnings("java:S106")
        CLI cli = new CLI(System.in, System.out, useCases);

        cli.start();
    }

}
