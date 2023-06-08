package hwr.oop.todo.library.todolist;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String itemName){
        super("Could not find the requested item: " + itemName);
    }

    public static NotFoundException withItemName(String itemName){
        return new NotFoundException(itemName);
    }
}
