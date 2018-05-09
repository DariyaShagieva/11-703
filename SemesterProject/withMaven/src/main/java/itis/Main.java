package itis;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 03.05.2018
 * itis.Main
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class Main {

    @Parameter(names = "-classFolder")
    private String classFolder;

    public static void main(String[] args) {
        Main main = new Main();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);
        main.run();
    }

    public void run() {
        try {
            Files.list(Paths.get(classFolder))
            .forEach(file -> {
                String type;
                Field [] fields = new Field[1];
                Method [] methods = new Method[1];
                if((type = file.getFileName().toString().split(" ")[1]).equals("class")){
                    try {
                        Class aClass = Class.forName(classFolder + "\\" + file.getFileName());
                        fields = aClass.getDeclaredFields();
                        methods = aClass.getDeclaredMethods();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    for (Field field : fields){
                        System.out.println(field.getType() + " " + field.getName());
                    }
                    for (Method method : methods){
                        System.out.println(method.getName() + " " + method.getParameters());
                    }
                }else {
                    if (type.equals("data")){
                        try {
                            Object o = new ObjectInputStream(new FileInputStream(classFolder + "\\" + file.getFileName())).readObject();
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }

            });
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
