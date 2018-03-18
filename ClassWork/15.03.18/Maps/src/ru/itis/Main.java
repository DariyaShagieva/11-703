package ru.itis;

public class Main {

    public static void main(String[] args) {
        Map<String, String> map = new HashMapImpl<>();
        map.put("Marsel", "Sidikov");
        System.out.println(map.get("Marsel"));
        map.put("Marsel", "Sitdikov");
        System.out.println(map.get("Marsel"));
        map.put("Dasha", "Shagieva");
        map.put("Anita", "Kurova");
        System.out.println(map.get("Anita"));
        map.put("Anita", "Kai");
        System.out.println(map.get("Anita"));
        map.put("Irina", "Skripka");
        map.put("Arina", "Kotik");
        map.put("Arina", "MEOW");
        map.put("Irina", "Trun'");
        map.put("Dasha", "Prosto straday <3");
        map.put("Irina", "Skripka");
        System.out.println();
        System.out.println();

        System.out.println(map.get("Marsel"));
        System.out.println(map.get("Anita"));
        System.out.println(map.get("Irina"));
        System.out.println(map.get("Arina"));
        System.out.println(map.get("Keksik"));
        System.out.println(map.get("Dasha"));
        int c = 0;
        c++;
    }
}
