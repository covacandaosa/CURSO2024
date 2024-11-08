package fp.dam.psp.unidad2.java.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Ejemplo1 {

    static class Contador {
        ReentrantLock lock = new ReentrantLock();
        private int n;
        public Contador(int n) {
            this.n = n;
        }
        public void inc() {
            lock.lock();
            try {
                // dentro del bloque try se recoge la sección crítica
                n++;
            } finally {
                lock.unlock();
            }
        }
        public int get() {
            return n;
        }
    }

    static void tarea() {
        for (int i=0; i<10; i++)
            c.inc();
    }

    static Contador c = new Contador(100);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i=0; i<10; i++)
            service.submit(Ejemplo1::tarea);
        service.shutdown();
        service.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("contador: " + c.get());
    }


}
