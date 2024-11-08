package fp.dam.psp.unidad2.java.util.concurrent;

/*
 * Escribir un programa concurrente que ejecute N hilos. Todos los hilos realizan
 * el mismo trabajo:
 *      • Imprimir una línea identificándose, anunciando el inicio de su ejecución y
 *        mostrando el tiempo que permanecerán dormidos.
 *      • Dormir durante el tiempo especificado para cada uno de ellos.
 *      • Imprimir una línea donde se identifiquen de nuevo para anunciar su finalización.
 * Cuando todos los hilos hayan finalizado su tarea, el hilo principal imprimirá un mensaje informando de ello.
 *
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ejercicio1 implements Runnable {

    long tiempo;

    public Ejercicio1(long tiempo) {
        this.tiempo = tiempo;
    }

    static void tarea() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("En la tarea");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
//        for (int i=0; i<20; i++)
//            service.submit(new Ejercicio1(i * 500));
        service.submit(Ejercicio1::tarea);
        service.shutdown();
        service.awaitTermination(1000000000L, TimeUnit.DAYS);
        System.out.println("Programa finalizado");
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " dormirá " + tiempo + " milisegundos");
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " ha finalizado");
    }
}
