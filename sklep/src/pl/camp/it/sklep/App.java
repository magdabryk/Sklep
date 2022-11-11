package pl.camp.it.sklep;

import pl.camp.it.sklep.engine.Engine;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        try {
            Engine.start();
        } catch(IOException e) {
            System.out.println("problem z wczytywaniem danych");
        }
    }
}
