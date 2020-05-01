package Rühmatöö;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static javafx.application.Application.launch;

public class Pizzapood extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage peaLava) throws IOException {
        ArrayList<Jook> joogid = new ArrayList<>(Arrays.asList(joogid()));
        ArrayList<Kate> katted = new ArrayList<>(Arrays.asList(katted()));
        ArrayList<Pitsa> pitsad = new ArrayList<>(Arrays.asList(pitsad(katted)));
        Menüü menüü = new Menüü();
        menüü.setJoogid(joogid.subList(joogid.size() / 2, joogid.size()));
        menüü.setPitsad(pitsad.subList(0, pitsad.size() / 2));
        BackgroundImage myBI = new BackgroundImage(new Image("https://i0.wp.com/friendsofchapman.org/wp-content/uploads/2017/08/pizza_stand.jpg?fit=800%2C600&ssl=1", 800, 600, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        BorderPane piir = new BorderPane();

        HBox ülemine = new HBox();
        //ülemine.setBackground(new Background(new BackgroundFill(Color.SNOW, CornerRadii.EMPTY, Insets.EMPTY)));
        ülemine.setAlignment(Pos.CENTER);
        Text tervitus = new Text("TERE TULEMAST RESTORANI PIZZAKIOSK 2!");
        tervitus.setStyle("-fx-background-color: rgba(255,255,255,0.5); -fx-background-radius: 10;");
        Light light = new Light.Distant();
        Lighting lighting = new Lighting();
        lighting.setLight(light);
        tervitus.setEffect(lighting);
        tervitus.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
        tervitus.setFill(Color.RED);
        ülemine.setPadding(new Insets(10, 0, 40, 0));
        ülemine.getChildren().add(tervitus);
        piir.setTop(ülemine);
        ;

        VBox küsimus = new VBox();
        küsimus.setPadding(new Insets(10, 100, 20, 140));
        Button enter = new Button("Sisesta");
        HBox sisestajaabi = new HBox();
        sisestajaabi.setSpacing(10);
        enter.setAlignment(Pos.BOTTOM_RIGHT);
        Text küsi = new Text(" Mida soovite tellida?");
        küsi.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 16));
        TextField sisend = new TextField("Tellimuse esitamiseks sisestage kõigepealt oma täisnimi...");
        sisend.setPrefWidth(450);
        sisestajaabi.getChildren().addAll(sisend, enter);
        sisend.addEventHandler(MouseEvent.MOUSE_CLICKED, me -> sisend.setText(""));

        tellimuseKüsija(enter, sisend);

        küsimus.getChildren().addAll(küsi, sisestajaabi);
        küsimus.setVisible(false);
        piir.setBottom(küsimus);
        piir.setStyle("-fx-border-color: #654321;" +
                "-fx-border-width: 5px;");

        VBox menyy = new VBox(5);
        //Button edasi = new Button("Joogid ->");
        menyy.setSpacing(20);
        Button näita = new Button("Menüü");
        näita.setMinWidth(100);
        Button telli = new Button("Telli kohe");
        telli.setMinWidth(100);
        menyy.setPadding(new Insets(0, 0, 20, 0));
        Text kuvaja2 = new Text("Menüü nägemiseks vajuta Menüü nupule.");
        kuvaja2.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 12));
        Text kuvaja3 = new Text("Tellimiseks vajuta Telli nupule.");
        kuvaja3.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 12));
        menyy.setAlignment(Pos.TOP_CENTER);
        menyy.setPadding(new Insets(0, 0, 0, 0));
        menyy.setStyle("-fx-background-color: rgba(255,255,255,0.8); -fx-background-radius: 10;");
        menyy.getChildren().addAll(kuvaja3, kuvaja2, näita, telli);
        menyy.setVisible(false);
        telli.addEventHandler(MouseEvent.MOUSE_CLICKED, me -> küsimus.setVisible(true));
        näita.addEventHandler(MouseEvent.MOUSE_CLICKED, me -> menyyhandler(kuvaja2, näita, menüü));
        piir.setCenter(menyy);

        VBox tegevus = new VBox();
        tegevus.setAlignment(Pos.CENTER);
        tegevus.setSpacing(40);
        tegevus.setPadding(new Insets(20));
        Button menüünupp = new Button("Alusta tellimist");
        menüünupp.setScaleY(2);
        Button exit = new Button("Välju");
        exit.setScaleY(2);
        menüünupp.addEventHandler(MouseEvent.MOUSE_CLICKED, me -> naitaja(menyy, küsimus, menüünupp));
        exit.addEventHandler(MouseEvent.MOUSE_CLICKED, me -> System.exit(0));
        menüünupp.setMaxWidth(Double.MAX_VALUE);
        exit.setMaxWidth(Double.MAX_VALUE);
        tegevus.getChildren().addAll(menüünupp, exit);

        VBox parempoolne = new VBox();
        Text tühi = new Text("Made by \n Joosep Tavits \n& Jakob Univer \n ツ");
        tühi.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 12));
        parempoolne.setAlignment(Pos.BOTTOM_CENTER);
        parempoolne.setSpacing(10);
        parempoolne.getChildren().add(tühi);
        parempoolne.setPadding(new Insets(0, 23, 0, 23));
        tegevus.setPadding(new Insets(20));

        piir.setLeft(tegevus);
        piir.setBackground(new Background(myBI));
        piir.setRight(parempoolne);

        Scene stseen1 = new Scene(piir, 800, 600, Color.SNOW);

        peaLava.setTitle("Pizzapood");
        peaLava.setScene(stseen1);
        peaLava.show();
    }

    private void tellimuseKüsija(Button enter, TextField sisend) {
        final int[] tellitav = {1};
        final String[] nimi = {""};
        final String[] pitsadeString = {""};
        final String[] jookideString = {""};

        enter.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(tellitav[0] == 1) {
                    nimi[0] = sisend.getText();
                    if(!nimi[0].equals("")) {
                        sisend.setText("Sisestage soovitud pitsad...");
                        tellitav[0]++;
                        System.out.println(nimi[0]);
                        System.out.println(pitsadeString[0]);
                        System.out.println(jookideString[0]);
                    }
                    else{
                        sisend.setText("Meil on tellimuse jaoks teie nime vaja!");
                    }
                }
                else if(tellitav[0] == 2) {
                    pitsadeString[0] = sisend.getText();
                    sisend.setText("Sisestage soovitud joogid...");
                    tellitav[0]++;
                    System.out.println(nimi[0]);
                    System.out.println(pitsadeString[0]);
                    System.out.println(jookideString[0]);
                }
                else if(tellitav[0] == 3) {
                    jookideString[0] = sisend.getText();
                    if(!pitsadeString[0].equals("") || !jookideString[0].equals("")) {
                        sisend.setText("Tänan! Hakkame kohe tellimust valmistama.");
                        tellitav[0]++;
                        System.out.println(nimi[0]);
                        System.out.println(pitsadeString[0]);
                        System.out.println(jookideString[0]);
                    }
                    else{
                        sisend.setText("Midagi te peate ikka tellima! Sisestage soovitud pitsad...");
                        tellitav[0] = 2;
                    }
                }
            }
        });
        sisend.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    if(tellitav[0] == 1) {
                        nimi[0] = sisend.getText();
                        if(!nimi[0].equals("")) {
                            sisend.setText("Sisestage soovitud pitsad...");
                            tellitav[0]++;
                            System.out.println(nimi[0]);
                            System.out.println(pitsadeString[0]);
                            System.out.println(jookideString[0]);
                        }
                        else{
                            sisend.setText("Meil on tellimuse jaoks teie nime vaja!");
                        }
                    }
                    else if(tellitav[0] == 2) {
                        pitsadeString[0] = sisend.getText();
                        sisend.setText("Sisestage soovitud joogid...");
                        tellitav[0]++;
                        System.out.println(nimi[0]);
                        System.out.println(pitsadeString[0]);
                        System.out.println(jookideString[0]);
                    }
                    else if(tellitav[0] == 3) {
                        jookideString[0] = sisend.getText();
                        if(!pitsadeString[0].equals("") || !jookideString[0].equals("")) {
                            sisend.setText("Tänan! Hakkame kohe tellimust valmistama.");
                            tellitav[0]++;
                            System.out.println(nimi[0]);
                            System.out.println(pitsadeString[0]);
                            System.out.println(jookideString[0]);
                        }
                        else{
                            sisend.setText("Midagi te peate ikka tellima! Sisestage soovitud pitsad...");
                            tellitav[0] = 2;
                        }
                    }
                }
            }
        });
    }

    public static void menyyhandler(Text text, Button button, Menüü menu) {
        List<Pitsa> abistaja = menu.getPitsad();
        List<Jook> joogiabi = menu.getJoogid();
        StringBuilder ehitaja = new StringBuilder();
        StringBuilder joogiehitaja = new StringBuilder();
        for (Jook elem : joogiabi)
            joogiehitaja.append(elem.toString().replaceAll(" ", ""));
        for (Pitsa elem : abistaja)
            ehitaja.append(elem.toString().replaceAll(" ", ""));
        if (button.getText().equals("Menüü") || button.getText().equals("Pitsad")) {
            text.setText(ehitaja.toString());
            button.setText("Joogid");
        } else if (button.getText().equals("Joogid")) {
            text.setText(joogiehitaja.toString());
            button.setText("Pitsad");
        }
    }

    public static void naitaja(Node menyy, Node kusimus, Button nupp) {
        if (nupp.getText().equals("Alusta tellimist")) {
            menyy.setVisible(true);
            nupp.setText("Tagasi");
            nupp.setMinWidth(100);
        } else {
            menyy.setVisible(false);
            kusimus.setVisible(false);
            nupp.setText("Alusta tellimist");
            nupp.setMinWidth(100);
        }

    }

    /*public static void suhtlus(ArrayList<Pitsa> pitsad, Menüü menüü, ArrayList<Jook> joogid, ArrayList<Kate> katted)
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
    }*/

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
            System.out.println("Vali kate nr " + (i + 1) + ".");
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
            } else {
                break;
            }
        }
        return erinev;
    }
}