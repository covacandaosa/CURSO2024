package fp.dam.psp.unidad2.ejemplos.ejemplo1.v2;

public class UnHilo extends Thread {

    public UnHilo() {
        super();
    }

    public UnHilo(String name) {
        super(name);
    }

    public UnHilo(ThreadGroup group, String name) {
        super(group, name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
            System.out.println(Thread.currentThread().getName() + ", mensaje " + i);
        }
    }

}
