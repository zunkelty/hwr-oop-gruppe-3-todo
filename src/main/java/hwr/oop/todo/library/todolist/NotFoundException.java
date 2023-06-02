package hwr.oop.todo.library.todolist;

public class NotFoundException extends RuntimeException{
    public NotFoundException(){
        super("Could not find the requested item.");
    }
}
