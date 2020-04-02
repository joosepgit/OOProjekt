package Rühmatöö;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/*LOE SEDA :D
 * Nii, pmst tegin siia palju isendeid igast klassist, suuruse ja hinna suhtega mängimine
 * tõmbas mähisesse lõpuks, ei saagi nüüd aru kas me peaksmine menüü jaoks eraldi
 * järjendid tegema või selle suuruse jaoks ikkagi mingi muu lahenduse leidma,
 * liidest ei osanud kuidagi sisse tuua hetkel. Kirjutasin mingi esialgse kasutajaga
 * suhtluse ka valmis, siin samas klassis kõige all. Pitsal iseenesest on
 * suuruse kaudu hinna määramine korras, aga menüüs kuvamine võiks ikkagi ühekordne olla...
 * joogiga ei teagi praegu kuidas võiks teha. Tellimuse klass on iseenesest suht
 * kasutu kui tal on parameetriks juba terve tellimuse hind, tellimuse klassi
 * eesmärk võikski äkki olla selle koguhinna arvutamine kõige lõpus? Mõtlesin suhtluse osas
 * nii teha, et kõik kasutaja sisendid salvestatakse esialgu muutujatesse, siis tehakse
 * temast tellimuse isend, tagastatakse täishind ja salvestatkse n-ö käsil olevate
 * tellimuste massiivi. Tellimuse valmis saamine võiks võtta mingi 30 sekundit.
 * Tellimuse sisestamise while loop võiks kesta kuni kasutaja sisestab lõpp.
 * Vastasel juhul võtab peaklass lihtsalt järjest uusi tellimusi. Pitsa, kate ja menüü
 * klassid on enamvähem korras, tellimuse ja joogiga tuleb tegeleda, ja nagu üleval
 * öeldud, siis suurusele võiks parem lahendus saada :D
 *
 * GLHF, kuskil veits enne 14.00 liitun homme probs!
 * Joosep
 * */


public class Pizzapood {
    public static void main(String[] args) {
        ArrayList<Jook> joogid = new ArrayList<>(Arrays.asList(joogid()));
        ArrayList<Kate> katted = new ArrayList<>(Arrays.asList(katted()));
        ArrayList<Pitsa> pitsad = new ArrayList<>(Arrays.asList(pitsad(katted)));
        Menüü menüü = new Menüü();
        menüü.setJoogid(joogid);
        menüü.setPitsad(pitsad);
        suhtlus(pitsad, menüü, joogid);
    }

    public static void suhtlus(ArrayList<Pitsa> pitsad, Menüü menüü, ArrayList<Jook> joogid) {
        String tellijaNimi;
        ArrayList<Pitsa> tellitudpitsad = new ArrayList<>();
        ArrayList<Jook> tellitudjoogid = new ArrayList<>();
        while (true) {
            System.out.println("Tere tulemast restorani pizzakiosk 2!");
            Scanner suhtlus = new Scanner(System.in);
            System.out.println("Menüü nägemiseks vajutage enterit...");
            suhtlus.nextLine();
            System.out.println(menüü.toString());
            System.out.println("Vajutage enterit, kui olete valmis tellima...");
            suhtlus.nextLine();
            System.out.println("Tellimuse esitamiseks sisestage kõigepealt oma täisnimi");
            tellijaNimi = suhtlus.nextLine();
            System.out.println("Sisestage valitud pitsa nimetus ja suurus 'V' -> (VÄIKE) või 'S' -> (SUUR)." +
                    " \nKui soovite mitut erinevat, siis eraldage komadega.");
            String[] pitsadestring = suhtlus.nextLine().split(",");
            for (int i = 0; i < pitsadestring.length; i++) {
                String nimetus = pitsadestring[i].split(" ")[0];
                String suurus = pitsadestring[i].split(" ")[1];
                for (int l = 0; l < pitsad.size(); l++) {
                    if (nimetus.equals(pitsad.get(l).getNimetus()) && suurus.equals(pitsad.get(l).getSuurus())) {
                        tellitudpitsad.add(pitsad.get(l));
                    }
                }
            }
            System.out.println("Sisestage valitud joogi nimetus ja suurus 'V' -> (VÄIKE) või 'S' -> (SUUR)." +
                    " \nKui soovite mitut erinevat, siis eraldage komadega.");
            String[] jookidestring = suhtlus.nextLine().split(",");
            for (int i = 0; i < jookidestring.length; i++) {
                String nimetus = jookidestring[i].split(" ")[0];
                String suurus = jookidestring[i].split(" ")[1];
                for (int l = 0; l < joogid.size(); l++) {
                    if (nimetus.equals(joogid.get(l).getNimetus()) && suurus.equals(joogid.get(l).getSuurus())) {
                        tellitudjoogid.add(joogid.get(l));
                    }
                }
            }
            Tellimus uus = new Tellimus(tellijaNimi, tellitudpitsad, tellitudjoogid);
            System.out.println(uus.toString());
            System.out.println("Tellimuse hind: "+ uus.koguhind());
            System.out.println("Kui soovite veel midagi tellida, vajutage enterit, kui ei, kirjutage 'Stopp' ning siis " +
                    "vajutage enterit.");
            String vastus = suhtlus.nextLine();
            if (vastus.toLowerCase().equals("stopp")){
                break;
            }
        }
    }
    public static Jook[] joogid(){
        Jook limonaad = new Jook("Limonaad", "V", 2);
        Jook vesi = new Jook("Vesi", "V", 0.5);
        Jook õlu = new Jook("Õlu", "V", 3.5);
        Jook piim = new Jook("Piim", "V", 1.5);
        Jook mahl = new Jook("Mahl", "V", 3);
        Jook slimonaad = new Jook("Limonaad", "S", 4);
        Jook svesi = new Jook("Vesi", "S", 1);
        Jook sõlu = new Jook("Õlu", "S", 7);
        Jook spiim = new Jook("Piim", "S", 3);
        Jook smahl = new Jook("Mahl", "S", 6);
        Jook[] joogid = {limonaad, vesi, õlu, piim, mahl,
                slimonaad, svesi, sõlu, spiim, smahl};
        return joogid;
    }
    public static Kate[] katted(){
        Kate sai = new Kate("Sai", 1.5);
        Kate kaste = new Kate("Ketšup", 0.5);
        Kate juust = new Kate("Juust", 2.5);
        Kate hakkliha = new Kate("Hakkliha", 2);
        Kate salaami = new Kate("Salaami", 2);
        Kate paprika = new Kate("Paprika", 0.5);
        Kate ananass = new Kate("Ananass", 0.8);
        Kate seen = new Kate("Šampinjon", 1);
        Kate[] katted = {sai, kaste, juust, hakkliha, salaami, paprika, ananass, seen};
        return katted;
    }
    public static Pitsa[] pitsad(ArrayList<Kate> katted){
        Kate[] lihamassiiv = {katted.get(0), katted.get(1), katted.get(2), katted.get(3), katted.get(5)};
        Kate[] vorstmassiiv = {katted.get(0), katted.get(1), katted.get(2), katted.get(4), katted.get(6)};
        Kate[] seenemassiiv = {katted.get(0), katted.get(1), katted.get(2), katted.get(7)};
        Kate[] suvalinemassiiv = {katted.get(0), katted.get(1), katted.get(2)};
        ArrayList<Kate> liha = new ArrayList<>(Arrays.asList(lihamassiiv));
        ArrayList<Kate> vorst = new ArrayList<>(Arrays.asList(vorstmassiiv));
        ArrayList<Kate> seene = new ArrayList<>(Arrays.asList(seenemassiiv));
        ArrayList<Kate> suvaline = new ArrayList<>(Arrays.asList(suvalinemassiiv));
        Pitsa Hakklihapitsa = new Pitsa(liha, "V", "Hakklihapitsa");
        Pitsa Salaamipitsa = new Pitsa(vorst, "V", "Salaamipitsa");
        Pitsa Šampinjonipitsa = new Pitsa(seene, "V", "Šampinjonipitsa");
        Pitsa Omalooming = new Pitsa(suvaline, "V", "Omalooming");
        Pitsa sHakklihapitsa = new Pitsa(liha, "S", "Hakklihapitsa");
        Pitsa sSalaamipitsa = new Pitsa(vorst, "S", "Salaamipitsa");
        Pitsa sŠampinjonipitsa = new Pitsa(seene, "S", "Šampinjonipitsa");
        Pitsa sOmalooming = new Pitsa(suvaline, "S", "Omalooming");
        Pitsa[] pitsad = {Hakklihapitsa, Salaamipitsa, Šampinjonipitsa, Omalooming,
                sHakklihapitsa, sSalaamipitsa, sŠampinjonipitsa, sOmalooming};
        return pitsad;
    }
}