import pomiary.Statystyki;

import java.util.Arrays;
import java.util.Random;


public class LAB02 {

    public static void main(String[] args) throws InterruptedException {
        int[] wektor = stworzPosortowanaTablice(100000);
        int szukana = 56666;
        int znalezionyIndeks = -1;
        double[] pomiary = new double[100000];
        int liczbaRdzeni = 1;
        int liczbaElementowNaWatek = (wektor.length / liczbaRdzeni) + (wektor.length % liczbaRdzeni);
        int wyjsciowaLiczbaWatkow;
        WyszukiwanieBinarneWatek[] watkiSzukajace = new WyszukiwanieBinarneWatek[liczbaRdzeni];

        for (int k = 0; k < pomiary.length; k++) {
            wyjsciowaLiczbaWatkow = Thread.activeCount();


            long poczatekPomiarow = System.nanoTime();
            long koniecPomiarow = 0;
            for (int i = 0, j = 0; i < liczbaRdzeni; i++) {
                watkiSzukajace[i] = new WyszukiwanieBinarneWatek(wektor, szukana, j, j += liczbaElementowNaWatek);
                watkiSzukajace[i].start();
            }

            while (Thread.activeCount() != wyjsciowaLiczbaWatkow) {
                for (WyszukiwanieBinarneWatek wyszukiwanieBinarneWatek : watkiSzukajace) {
                    if (wyszukiwanieBinarneWatek.getZnalezionyIndeks() != -1) {
                        znalezionyIndeks = wyszukiwanieBinarneWatek.getZnalezionyIndeks();
                    }
                }
                if (znalezionyIndeks != -1) {
                    koniecPomiarow = System.nanoTime();
                    break;
                }

            }
            if (koniecPomiarow == 0) {
                koniecPomiarow = System.nanoTime();
            }

            pomiary[k] = (koniecPomiarow - poczatekPomiarow);
        }
        Statystyki statystyki = new Statystyki(pomiary, 100000);
        System.out.println("Srednia arytmetyczna: " + statystyki.getSredniaArytmetyczna());
        System.out.println("Odchylenie standardowe: " + statystyki.getOdchylenieStandardowe());
        System.out.println("Blad wzgledny: " + statystyki.getBladWzgledny());
        System.out.println("Uogolniona zlozonosc obliczeniowa: " + statystyki.getUogolnionaZlonoscObliczeniowa());
        System.out.println("Znaleziony indeks: " + znalezionyIndeks);
    }


    private static int[] stworzPosortowanaTablice(int liczbaElementow) {
        int[] wektor = new int[liczbaElementow];
        Random random = new Random(67);
        for (int i = 0; i < wektor.length; i++) {
            wektor[i] = random.nextInt(liczbaElementow);
        }
        Arrays.sort(wektor);
        return wektor;
    }


}
    
