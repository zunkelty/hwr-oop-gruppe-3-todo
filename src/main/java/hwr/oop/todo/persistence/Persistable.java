package hwr.oop.todo.persistence;

public interface Persistable {
    String toCSV();
    void fromCSV(String csv);
}
