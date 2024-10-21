package fp.dam.psp.unidad2.ejemplos.ejemplo9;

public class Contador {

    private static Object monitor = new Object();
    private Integer n;

    public Contador(int n) {
        this.n = n;
    }

    public void inc() {
        synchronized (n) {
            n++;
        }
    }

    public int get() {
        return n;
    }
}
