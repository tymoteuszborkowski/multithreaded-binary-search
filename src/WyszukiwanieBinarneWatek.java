public class WyszukiwanieBinarneWatek extends Thread {

    private volatile int[] wektor;
    private int szukana;
    private int poczatekPrzedzialu;
    private int koniecPrzedzialu;
    private int znalezionyIndeks = -1;

    public WyszukiwanieBinarneWatek(int[] wektor, int szukana, int poczatekPrzedzialu, int koniecPrzedzialu) {
        this.wektor = wektor;
        this.szukana = szukana;
        this.poczatekPrzedzialu = poczatekPrzedzialu;
        this.koniecPrzedzialu = koniecPrzedzialu >= wektor.length ? this.koniecPrzedzialu = wektor.length-1 : koniecPrzedzialu;
    }

    @Override
    public void run() {
        wyszukiwanieBinarne();
    }

    private void wyszukiwanieBinarne() {

        int srodek = (koniecPrzedzialu - 1) / 2;
        while (Math.abs(koniecPrzedzialu - poczatekPrzedzialu) != 1) {

            if (wektor[srodek] == szukana) {
                znalezionyIndeks = srodek;
                break;
            } else {

                if (szukana < wektor[srodek]) {
                    koniecPrzedzialu = srodek;
                } else {
                    poczatekPrzedzialu = srodek;
                }
                srodek = (koniecPrzedzialu + poczatekPrzedzialu) / 2;
            }
        }
    }

    public int getZnalezionyIndeks() {
        return znalezionyIndeks;
    }
}
