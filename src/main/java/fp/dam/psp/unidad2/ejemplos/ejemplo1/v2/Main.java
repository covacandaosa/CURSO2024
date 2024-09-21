package fp.dam.psp.unidad2.ejemplos.ejemplo1.v2;

public class Main {

    public static void main(String[] args) {
        Thread t = new UnHilo("pepe");
        t.start();
        t.run();
    }

}
