package pomiary;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Statystyki {

    private static final String PLIK_WYJSCIOWY = "Statystyki.csv";
    private static final String CSV_DELIMITER = ",";
    private static final String SREDNIA_ARYTMETYCZNA = "Srednia arytmetyczna";
    private static final String ODCHYLENIE_STANDARDOWE = "Odchylenie standardowe";
    private static final String BLAD_WZGLEDNY = "Blad wzgledny";

    private double[] pomiary;
    private int rozmiarTablicyDanych;

    private double sredniaArytmetyczna;
    private double odchylenieStandardowe;
    private double bladWzgledny;
    private double uogolnionaZlonoscObliczeniowa;


    public Statystyki(double[] pomiary, int rozmiarTablicyDanych) {
        this.pomiary = pomiary;
        this.rozmiarTablicyDanych = rozmiarTablicyDanych;
        this.sredniaArytmetyczna = obliczSredniaArytmetyczna();
        this.odchylenieStandardowe = obliczOdchylenieStandardowe();
        this.bladWzgledny = obliczBladWzgledny();
        this.uogolnionaZlonoscObliczeniowa = obliczUogolnionaZlozonoscObliczeniowa();
    }


    public double getOdchylenieStandardowe() {
        return odchylenieStandardowe;
    }

    public double getSredniaArytmetyczna() {
        return sredniaArytmetyczna;
    }

    public double getBladWzgledny() {
        return bladWzgledny;
    }

    public double getUogolnionaZlonoscObliczeniowa() {return uogolnionaZlonoscObliczeniowa; }

    public void zapiszWynikiDoPliku() {

        try (PrintWriter writer = new PrintWriter(PLIK_WYJSCIOWY)) {
            writer.println(SREDNIA_ARYTMETYCZNA + CSV_DELIMITER + ODCHYLENIE_STANDARDOWE + CSV_DELIMITER + BLAD_WZGLEDNY);
            writer.println(sredniaArytmetyczna + CSV_DELIMITER + odchylenieStandardowe + CSV_DELIMITER + bladWzgledny);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private double obliczSredniaArytmetyczna() {
        double suma = 0;

        for (int i = 0; i < pomiary.length; i++) {
            suma += pomiary[i];
        }
        return suma / pomiary.length;
    }

    private double obliczOdchylenieStandardowe() {

        double odchylenie = 0;
        double sredniaArytmetyczna = obliczSredniaArytmetyczna();

        for (int i = 0; i < pomiary.length; i++) {
            odchylenie += Math.pow(pomiary[i] - sredniaArytmetyczna, 2);
        }
        odchylenie /= pomiary.length - 1;

        return Math.sqrt(odchylenie);
    }

    private double obliczBladWzgledny() {
        return (odchylenieStandardowe / sredniaArytmetyczna) * 100;
    }

    private double obliczUogolnionaZlozonoscObliczeniowa(){
        return Math.pow(10,9) * sredniaArytmetyczna * rozmiarTablicyDanych;
    }

}
