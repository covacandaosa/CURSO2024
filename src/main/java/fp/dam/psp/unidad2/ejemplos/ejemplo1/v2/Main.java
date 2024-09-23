package fp.dam.psp.unidad2.ejemplos.ejemplo1.v2;

public class Main {

    public static void main(String[] args) {
        Thread t1 = new fp.dam.psp.unidad2.ejemplos.ejemplo1.v1.UnHilo("pepe");
        Thread t2 = new fp.dam.psp.unidad2.ejemplos.ejemplo1.v1.UnHilo("pepín");
        t1.start();
        t2.start();
    }

}
