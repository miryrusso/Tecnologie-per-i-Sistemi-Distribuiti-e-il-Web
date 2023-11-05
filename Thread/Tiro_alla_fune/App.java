package Tiro_alla_fune;

import java.util.Random;

public class App {
    public static int posizione = 0;
    public static int vittorie_tp0 = 0;
    public static int vittorie_tp1 = 0;
    public static final Object lock = new Object();

    public static void main(String[] args) {
        Thread[] tp = new Thread[2];

        tp[0] = new Thread(new Runnable() {
            @Override
            public void run() {
                while (vittorie_tp0 < 10 && vittorie_tp1 < 10) {
                    int recupero = new Random().nextInt(4);
                    int forza = new Random().nextInt(6);

                    try {
                        Thread.sleep(recupero * 1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    synchronized (lock) {
                        if (posizione >= 10) {
                            vittorie_tp1++;
                            posizione = 0;
                            System.out.println("tp[0] ha vinto! Vittorie: " + vittorie_tp0 + " - " + vittorie_tp1);
                            lock.notify(); // Sveglia l'altro thread
                        } else {
                            posizione -= forza;
                            if (posizione <= -10) {
                                System.out.println("tp[0] ha vinto! Vittorie: " + vittorie_tp0 + " - " + vittorie_tp1);
                                lock.notify(); // Sveglia l'altro thread
                            }
                        }
                    }
                }
            }
        });

        tp[1] = new Thread(new Runnable() {
            @Override
            public void run() {
                while (vittorie_tp0 < 10 && vittorie_tp1 < 10) {
                    int recupero = new Random().nextInt(4);
                    int forza = new Random().nextInt(6);

                    try {
                        Thread.sleep(recupero * 1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    synchronized (lock) {
                        if (posizione <= -10) {
                            vittorie_tp0++;
                            posizione = 0;
                            System.out.println("tp[1] ha vinto! Vittorie: " + vittorie_tp0 + " - " + vittorie_tp1);
                            lock.notify(); // Sveglia l'altro thread
                        } else {
                            posizione += forza;
                            if (posizione >= 10) {
                                System.out.println("tp[1] ha vinto! Vittorie: " + vittorie_tp0 + " - " + vittorie_tp1);
                                lock.notify(); // Sveglia l'altro thread
                            }
                        }
                    }
                }
            }
        });

        tp[0].start();
        tp[1].start();
    }
}
