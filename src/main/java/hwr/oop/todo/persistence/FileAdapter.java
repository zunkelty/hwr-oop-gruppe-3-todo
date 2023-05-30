package hwr.oop.todo.persistence;

import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.tag.Tag;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class FileAdapter {

    public void save(String data, File file) {
        if (data == null) {
            throw new IllegalArgumentException("Data must not be null");
        }

        try {
            FileWriter fr = new FileWriter(file, true);
            BufferedWriter br = new BufferedWriter(fr);
            br.write(data);
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> load(String fileName) {
        List<String> strings = new ArrayList<>();

        try (FileReader reader = new FileReader(fileName); BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                strings.add(line);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        return strings;
    }
}
