import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 03.05.2018
 * Main
 *
 * @author Nita
 * @version v1.0
 */
public class Main {


    @Parameter(names = "-classFolder")
    private String classFolder = "C:\\Users\\Nita\\Desktop\\Classes";

    public static void main(String[] args) {
        Main main = new Main();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);
        main.run();
    }

    private void run() {
        try {
            Files.list(Paths.get(classFolder))
                    .forEach(file -> {
                        Field[] fields;
                        Method[] methods;
                        Constructor[] constructors;
                        System.out.println("________________________________");
                        System.out.println();
                        System.out.println("-- class file " + file.getFileName() + " class " + file.getFileName().toString().split("\\.")[0] + " --");
                        System.out.println("__________________");
                        System.out.println();
                        try {
                            Path current = Paths.get(file.getParent().toUri().normalize());
                            URL url = new URL("file:\\" + current + "\\");
                            URLClassLoader loader = URLClassLoader.newInstance(new URL[]{url}, getClass().getClassLoader());
                            String fileName = file.getFileName().toString();
                            if ((fileName.split("\\.")[1]).equals("class")) {
                                String name = fileName.split("\\.")[0];
                                Class aClass = loader.loadClass(name);
                                fields = aClass.getFields();
                                constructors = aClass.getConstructors();

                                System.out.println(">> Fields");
                                for (Field field : fields) {
                                    System.out.println(field.getType() + " " + field.getName());
                                }
                                System.out.println("__________________");
                                System.out.println();
                                soutConstructors(constructors);
                                System.out.println("__________________");

                                System.out.println(">> Methods");
                                methods = aClass.getMethods();
                                soutMethods(methods);
                                methods = aClass.getDeclaredMethods();
                                System.out.println(">> Declared Methods");
                                System.out.println();
                                soutMethods(methods);
                            }


                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                    });
            System.out.println("Do you want to create object? Yes/No");
            Scanner in = new Scanner(System.in);
            String input;
            int count;
            String creatable;
            Constructor[] constructors;
            try {
                while ((input = in.nextLine()).equals("Yes")) {
                    System.out.println("What do you want to create? Enter className");
                    Files.list(Paths.get(classFolder))
                            .forEach(file -> {
                                if (file.getFileName().toString().split("\\.")[1].equals("class")) {
                                    System.out.println(file.getFileName().toString().split("\\.")[0]);
                                }
                            });
                    creatable = in.nextLine();
                    URL url = new URL("file:\\" + classFolder + "\\");
                    URLClassLoader loader = URLClassLoader.newInstance(new URL[]{url}, getClass().getClassLoader());
                    Class aClass = loader.loadClass(creatable);
                    constructors = aClass.getConstructors();
                    soutConstructors(constructors);
                    System.out.println("Which the constructor do you want to use?");
                    //т.к. люди считают по нормальному, а мы с 0
                    count = Integer.parseInt(in.nextLine()) - 1;
                    Object[] objects = new Object[constructors[count].getParameterCount()];
                    int parNum = constructors[count].getParameterCount();
                    in.reset();
                    String toIn;
                    String toCast;
                    for (int c = 0; c < parNum; c++) {
                        String classType = constructors[count].getParameterTypes()[c].toString();
                        toCast = classType.split(" ")[1].split("\\.")[classType.split(" ")[1].split("\\.").length - 1];
                        System.out.println("Enter " + toCast);
                        switch (toCast) {
                            case "Integer":
                                objects[c] = Integer.parseInt(in.nextLine());
                                break;
                            case "String":
                                objects[c] = in.nextLine();
                                break;
                            case "BigDecimal":
                                objects[c] = BigDecimal.valueOf(Long.parseLong(in.nextLine()));
                                break;
                            case "BigInteger":
                                objects[c] = BigInteger.valueOf(Long.parseLong(in.nextLine()));
                                break;
                            case "Boolean":
                                objects[c] = Boolean.parseBoolean(in.nextLine());
                                break;
                            case "Byte":
                                objects[c] = Byte.parseByte(in.nextLine());
                                break;
                            case "Double":
                                objects[c] = Double.parseDouble(in.nextLine());
                                break;
                            case "Float":
                                objects[c] = Float.parseFloat(in.nextLine());
                                break;
                            case "Long":
                                objects[c] = Long.parseLong(in.nextLine());
                                break;
                            case "Char":
                                objects[c] = (char)Byte.parseByte(in.nextLine());
                                break;
                        }
                    }
                    try {
                        Object a = constructors[count].newInstance(objects);
                        System.out.println("Enter filename");
                        String filename;
                        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream((filename = in.nextLine()) + ".bin"));
                        stream.writeObject(a);
                        System.out.println("Enter path for file");
                        //TODO потом добавлю
                        File whereTo = new File(in.nextLine());
                        File currentFileToPath = new File(filename);
                        currentFileToPath.renameTo(new File(whereTo, filename));
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Do you want to create another one object? Yes/No");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static void soutMethods(Method[] methods) {
        for (Method method : methods) {
            System.out.print(method.getName() + " (");
            System.out.print(Arrays.toString(method.getParameterTypes()));
            System.out.println(")");
        }
        System.out.println("__________________");
    }

    private static void soutConstructors(Constructor[] constructors) {
        System.out.println(">> Constructors");
        int count = 1;
        for (Constructor constructor : constructors) {
            System.out.print(count++ + ". " + constructor.getName() + " ( ");
            java.lang.reflect.Parameter[] names = constructor.getParameters();
            Class[] parameters = constructor.getParameterTypes();
            for (java.lang.reflect.Parameter parameter : names) {
                System.out.print(parameter.toString() + "; ");
            }
            System.out.print(")");
            System.out.println();
        }
    }
}
