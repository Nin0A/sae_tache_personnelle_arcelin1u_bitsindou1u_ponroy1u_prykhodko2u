package classes;
//classe temporelle pour tester les classes

public class Main {
    public static void main(String[] args) {
        TacheMere t = new TacheMere("Hello", 7);
        TacheMere s = new TacheMere("Hello", 10);
        System.out.println(t);
        System.out.println(t.equals(s));
    }
}
