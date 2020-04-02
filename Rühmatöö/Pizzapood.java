package Rühmatöö;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Pizzapood {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Jook> joogid = new ArrayList<>(Arrays.asList(joogid()));
        ArrayList<Kate> katted = new ArrayList<>(Arrays.asList(katted()));
        ArrayList<Pitsa> pitsad = new ArrayList<>(Arrays.asList(pitsad(katted)));
        Menüü menüü = new Menüü();
        menüü.setJoogid(joogid.subList(joogid.size() / 2, joogid.size()));
        menüü.setPitsad(pitsad.subList(0, pitsad.size() / 2));
        suhtlus(pitsad, menüü, joogid, katted);
    }

    public static void suhtlus(ArrayList<Pitsa> pitsad, Menüü menüü, ArrayList<Jook> joogid, ArrayList<Kate> katted)
            throws InterruptedException {
        String tellijaNimi;
        ArrayList<String> tellimused = new ArrayList<>();
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
            if (!pitsadestring[0].equals("")) {
                for (String sõne : pitsadestring) {
                    String nimetus = sõne.trim().split(" ")[0];
                    String suurus = sõne.trim().split(" ")[1];
                    for (Pitsa pitsa : pitsad) {
                        if (nimetus.toLowerCase().equals("omalooming")) {
                            tellitudpitsad.add(omalooming(suurus, suhtlus, katted));
                            break;
                        } else if (nimetus.equals(pitsa.getNimetus()) && suurus.equals(pitsa.getSuurus())) {
                            tellitudpitsad.add(pitsa);
                        }
                    }
                }
            }
            System.out.println("Sisestage valitud joogi nimetus ja suurus 'V' -> (VÄIKE) või 'S' -> (SUUR)." +
                    " \nKui soovite mitut erinevat, siis eraldage komadega.");
            String[] jookidestring = suhtlus.nextLine().split(",");
            if (!jookidestring[0].equals("")) {
                for (String sõne : jookidestring) {
                    String nimetus = sõne.trim().split(" ")[0];
                    String suurus = sõne.trim().split(" ")[1];
                    for (Jook jook : joogid) {
                        if (nimetus.equals(jook.getNimetus()) && suurus.equals(jook.getSuurus())) {
                            tellitudjoogid.add(jook);
                        }
                    }
                }
            }
            Tellimus uus = new Tellimus(tellijaNimi, tellitudpitsad, tellitudjoogid);
            System.out.println(uus.tellitud());
            System.out.println();
            if (Math.random() * 100 > 10.0)
                System.out.println("HIND KOKKU: " + uus.koguhind());
            else {
                System.out.println("HIND KOKKU: " + Math.round(Math.random() * 25 + 5));
            }
            tellimused.add(uus.getTellijaNimi() + " " + uus.koguhind());
            uus.getJoogid().clear();
            uus.getPitsad().clear();
            System.out.println("Kui soovite veel midagi tellida, vajutage enterit, kui ei, kirjutage 'Stopp' ning siis " +
                    "vajutage enterit.");
            String vastus = suhtlus.nextLine();
            if (vastus.toLowerCase().equals("stopp")) {
                break;
            }
        }
        System.out.println("Alustati tellimuste täitmist.");
        while (!tellimused.isEmpty()) {
            Thread.sleep(10000);
            System.out.println("Tellimus " + tellimused.get(0) + " valmis!");
            tellimused.remove(tellimused.get(0));
        }
        Scanner lõpp = new Scanner(System.in);
        System.out.println("Kõik tellimused täidetud.\nUuesti alustamiseks vajutage enterit, lõpetamiseks kirjutage 'stopp'");
        String valik = lõpp.nextLine();
        if (valik.equals(""))
            suhtlus(pitsad, menüü, joogid, katted);
        else {
            System.out.println("Lahkusite poest, rõõmsa jällenägemiseni!");
        }
    }

    public static Jook[] joogid() {
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
        return new Jook[]{limonaad, vesi, õlu, piim, mahl,
                slimonaad, svesi, sõlu, spiim, smahl};
    }

    public static Kate[] katted() {
        Kate sai = new Kate("Sai", 1.5);
        Kate kaste = new Kate("Ketšup", 0.5);
        Kate juust = new Kate("Juust", 2.5);
        Kate hakkliha = new Kate("Hakkliha", 2);
        Kate salaami = new Kate("Salaami", 2);
        Kate paprika = new Kate("Paprika", 0.5);
        Kate ananass = new Kate("Ananass", 0.8);
        Kate seen = new Kate("Šampinjon", 1);
        return new Kate[]{sai, kaste, juust, hakkliha, salaami, paprika, ananass, seen};
    }

    public static Pitsa[] pitsad(ArrayList<Kate> katted) {
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
        return new Pitsa[]{Hakklihapitsa, Salaamipitsa, Šampinjonipitsa, Omalooming,
                sHakklihapitsa, sSalaamipitsa, sŠampinjonipitsa, sOmalooming};
    }

    public static Pitsa omalooming(String suurus, Scanner suhtlus, ArrayList<Kate> katted) {
        Pitsa erinev = new Pitsa(new ArrayList<>(Arrays.asList(
                katted.get(0), katted.get(1), katted.get(2))), suurus, "Omalooming");
        for (int i = 0; i < 4; i++) {
            System.out.println("Katted: hakkliha, salaami, paprika, ananass, šampinjonid");
            System.out.println("Vali kate nr " + (i+1) + ".");
            System.out.println("Kui ei soovi rohkem katteid valida, vajutage ENTER.");
            String kate = suhtlus.nextLine().toLowerCase();
            if (kate.equals("hakkliha")) {
                erinev.lisaKate(katted.get(3));
            } else if (kate.equals("salaami")) {
                erinev.lisaKate(katted.get(4));
            } else if (kate.equals("paprika")) {
                erinev.lisaKate(katted.get(5));
            } else if (kate.equals("ananass")) {
                erinev.lisaKate(katted.get(6));
            } else if (kate.equals("šampinjonid")) {
                erinev.lisaKate(katted.get(7));
            }else{
                break;
            }
        }
        return erinev;
    }
}