package fp.dam.psp.unidad2.ejemplos.ejemplo11;

public class Almacen {
    private static final Object monitor = new Object();
    private Integer almacenados = 0;
    private String [] productos;
    public Almacen(int capacidad) {
        productos = new String[capacidad];
    }
    public void almacenar(String producto) {
        synchronized (monitor) {
            if (almacenados == productos.length) // almacén lleno
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                }
            productos[almacenados++] = producto;
            monitor.notify();
        }
    }

    public String retirar() {
        synchronized (monitor) {
            if (almacenados == 0) // almacén vacío
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                }
            String producto = productos[--almacenados];
            monitor.notify();
            return producto;
        }
    }

}