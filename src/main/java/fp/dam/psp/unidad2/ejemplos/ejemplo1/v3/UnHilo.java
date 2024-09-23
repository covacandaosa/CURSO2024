package fp.dam.psp.unidad2.ejemplos.ejemplo1.v3;

public class UnHilo extends Thread {

    public static void main(String[] args) {
        Thread t = new Thread("pepe") {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {}
                    System.out.println(Thread.currentThread().getName() + ", mensaje " + i);
                }
            }
        };
        t.start();
    }

}
