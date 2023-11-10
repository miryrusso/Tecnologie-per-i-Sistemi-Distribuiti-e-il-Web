package Tiro_alla_fune.Seconda_versione;

import java.util.Random;

public class app {

    public static posizione posizione = new posizione();
    public static int vittorietp0 = 0;
    public static int vittorietp1 = 0;
    public static int fineGioco = 0; 
    

    public static void main(String[] args) throws InterruptedException {
        Thread[] tp = new Thread[2];

        tp[0] = new Thread(new Runnable() {
            @Override
            public void run() {
                while (vittorietp0 < 10 && (fineGioco == 0)) {
                    Random rnd = new Random();
                    int recupero = rnd.nextInt(3);
                    int forza = rnd.nextInt(5);
                    try {
                        Thread.sleep(recupero * 1000);
                    } catch (InterruptedException e) {
                    }
                    ;
                    System.out.println("Posizione: " + posizione.get());
                    if (posizione.get() >= 10) {

                        System.out.println("Riconosce la vittoria di tp1");
                        
                        vittorietp1 = vittorietp1 + 1;
                        System.out.println("[AGGIORNAMENTO POSIZIONE TP1 " + vittorietp1 + " ]");

                        posizione.set(0);
                        System.out.println("[AGGIORNAMENTO POSIZIONE TP1 " + vittorietp1 + " ]");
                        // sveglia
                        System.out.println("SVEGLIAMO TP[1] ");
                        //tp[1].notify();
                        try {
                            //System.out.println("ATTESA DI TP[0]");
                            //tp[0].wait();
                            posizione.gameNotify();
                        } catch (InterruptedException e) {
                        }
                        ;
                    } else {
                        
                        posizione.set(posizione.get() - forza);
                        System.out.println("[T1] INCREMENTA POSIZIONE " +posizione.get());
                        if (posizione.get() <= -10) {
                            // si mette in attesa e hai vinto
                            System.out.println("[LA POSIZIONE È MINORE DI 10 QUINDI VITTORIA tp[0]]");

                            try {
                                System.out.println("ATTESA DI TP[0]");
                                //tp[0].wait();
                                posizione.gameWait();
                            } catch (InterruptedException e) {
                            }
                            ;
                        }
                    }
                }
                System.out.println("[T0] SETTA IL FINE GIOCO A 1 ");
                fineGioco = 1; 
            };
        });

        tp[1] = new Thread(new Runnable() {
            @Override
            public void run() {
                while (vittorietp1 < 10 && (fineGioco == 0)) {
                    Random rnd = new Random();
                    int recupero = rnd.nextInt(3);
                    int forza = rnd.nextInt(5);
                    try {
                        Thread.sleep(recupero * 1000);
                    } catch (InterruptedException e) {
                    }
                    ;

                    if (posizione.get() <= -10) {
                        System.out.println("Riconosce la vittoria di tp0 ");
                        vittorietp0++;
                        System.out.println("VITTORIA DI TP0 "+vittorietp0);
                        posizione.set(0);
                        System.out.println("SVEGLIA TP[0] ");
                        //tp[0].notify();
                        try {
                            //System.out.println("ATTESA DI TP[0]");
                            //tp[0].wait();
                            posizione.gameNotify();
                        } catch (InterruptedException e) {
                        }
                        ;
                    } else {

                        System.out.println("[T0] INCREMENTA POSIZIONE");
                        posizione.set(posizione.get() + forza);
                        System.out.println("POSIZIONE "+posizione.get());
                        if(posizione.get() >= 10){
                            System.out.println("[T1] ha vinto");
                        }
                    }
                }
                System.out.println("[T1] SETTA IL FINE GIOCO A 1 ");
               fineGioco = 1; 
            }
        });

        tp[0].start();
        tp[1].start();
        tp[0].join();
        tp[1].join();


        if(vittorietp0 > vittorietp1){
            System.out.println("Tp0 ha vinto " + vittorietp0);
        }else{
            System.out.println("Tp1 ha vinto " + vittorietp1);
        }




    }
}
