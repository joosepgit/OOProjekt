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
        //Lisame liidesele taustapildi ning seame talle mõned muutumisreeglid.
        BackgroundSize taustapilt = new BackgroundSize(800, 600, false, false, true, true);
        BackgroundImage myBI = new BackgroundImage(new Image("https://i0.wp.com/friendsofchapman.org/wp-content/uploads/2017/08/pizza_stand.jpg?fit=800%2C600&ssl=1", 800, 600, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                taustapilt);
        //Loome välise borderpane, kogu ülejäänud paigutus toimub selle borderpane osade sees.
        BorderPane piir = new BorderPane();

        //Loome horisontaalse paigutusega kasti, mis sisaldab tervitust.
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
        //Paigutame selle välise borderpane tippu.
        piir.setTop(ülemine);


        //Loome vertikaalse ehitusega osa, mis tegeleb tellimuse sisendi võtmisega.
        VBox küsimus = new VBox();
        küsimus.setAlignment(Pos.CENTER);
        küsimus.setPadding(new Insets(10, 100, 20, 100));
        Button enter = new Button("Sisesta");
        HBox sisestajaabi = new HBox();
        sisestajaabi.setAlignment(Pos.CENTER);
        sisestajaabi.setSpacing(10);
        enter.setAlignment(Pos.BOTTOM_RIGHT);
        Text küsi = new Text(" Mida soovite tellida?");
        küsi.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 16));
        TextField sisend = new TextField("Tellimuse esitamiseks sisestage kõigepealt oma täisnimi...");
        sisend.setPrefWidth(450);
        //Kui vajutada hiirega TextFieldile, siis kaob juhendav tekst.
        sisestajaabi.getChildren().addAll(sisend, enter);
        sisend.addEventHandler(MouseEvent.MOUSE_CLICKED, me -> sisend.setText(""));
        sisend.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
                    sisend.setText("");
                }
            }
        });

        tellimuseKüsija(enter, sisend, pitsad, joogid, katted);

        küsimus.getChildren().addAll(küsi, sisestajaabi);
        //Küsimus on esialgu nähtamatu, muutub nähtavaks kui kasutaja vajutab "Telli" nupule
        küsimus.setVisible(false);
        //Paigutame küsimuse välise borderpane kõige alumisse osasse.
        piir.setBottom(küsimus);
        //Lisame kogu aknale pruuni piiri, et näeks parem välja.
        piir.setStyle("-fx-border-color: #654321;" +
                "-fx-border-width: 5px;");

        VBox menyy = new VBox(5);
        //Button edasi = new Button("Joogid ->");
        menyy.setSpacing(20);
        Button näita = new Button("Menüü");
        näita.setMinWidth(100);
        Button telli = new Button("Telli kohe");
        telli.setMinWidth(100);
        Button lõpeta = new Button("Lõpeta tellimine");
        telli.setMinWidth(100);
        Text juhend = new Text("Pitsad ja joogid tuleb sisestada vormingus: \"toote_nimetus suurus(V/S)\"." +
                " \nErinevad tooted eraldada komaga.");
        juhend.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 12));
        menyy.setPadding(new Insets(0, 0, 20, 0));
        Text kuvaja2 = new Text("Menüü nägemiseks vajuta Menüü nupule.");
        kuvaja2.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 12));
        Text kuvaja3 = new Text("Tellimiseks vajuta Telli nupule.");
        kuvaja3.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 12));
        menyy.setAlignment(Pos.TOP_CENTER);
        menyy.setPadding(new Insets(0, 0, 0, 0));
        menyy.setStyle("-fx-background-color: rgba(255,255,255,0.8); -fx-background-radius: 10;");
        menyy.getChildren().addAll(kuvaja3, kuvaja2, näita, telli, lõpeta, juhend);
        menyy.setVisible(false);
        telli.addEventHandler(MouseEvent.MOUSE_CLICKED, me -> küsimus.setVisible(true));
        //Nupp näita reguleerib alljärgneva funktsiooni abil menüü nähtavust ning pitsade ja jookide vahel valimist.
        näita.addEventHandler(MouseEvent.MOUSE_CLICKED, me -> menyyhandler(kuvaja2, näita, menüü));
        //Menüü paigutame pealava keskele
        lõpeta.addEventHandler(MouseEvent.MOUSE_CLICKED, me -> tellimusteTäitmine());
        piir.setCenter(menyy);

        //Loome uue vertikaalse kasti, mille abil alustatakse tegevust.
        VBox tegevus = new VBox();
        tegevus.setAlignment(Pos.CENTER);
        tegevus.setSpacing(40);
        tegevus.setPadding(new Insets(20));
        Button menüünupp = new Button("Alusta tellimist");
        menüünupp.setScaleY(2);
        Button exit = new Button("Välju");
        exit.setScaleY(2);
        //Allolevad lambda avaldised reguleerivad tegevuse nuppude tööd.
        menüünupp.addEventHandler(MouseEvent.MOUSE_CLICKED, me -> naitaja(menyy, küsimus, menüünupp));
        exit.addEventHandler(MouseEvent.MOUSE_CLICKED, me -> System.exit(0));
        menüünupp.setMaxWidth(Double.MAX_VALUE);
        exit.setMaxWidth(Double.MAX_VALUE);
        tegevus.getChildren().addAll(menüünupp, exit);

        VBox parempoolne = new VBox();
        //VBox vasakusse äärde autorite nimedega.
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

        //Akna esialgne suurus ja sisu värv
        Scene stseen1 = new Scene(piir, 800, 600, Color.SNOW);

        //Akna suuruse piirangud
        peaLava.setMinWidth(820);
        peaLava.setMinHeight(640);
        peaLava.setMaxHeight(800);
        peaLava.setMaxWidth(1025);
        peaLava.setTitle("Pizzapood");
        peaLava.setScene(stseen1);
        peaLava.show();
    }

    private void tellimusteTäitmine() {
        ArrayList<String> tellimused = new ArrayList<>();
        File tellimus = new File("tellimused.txt");
        try (Scanner lugeja = new Scanner(tellimus, "UTF-8")) {
            while (lugeja.hasNextLine()) {
                tellimused.add(lugeja.nextLine());
            }
            PrintWriter pw = new PrintWriter("tellimused.txt");
            pw.close();
        } catch (IOException e) {
            System.out.println("Jama faili lugemisel!");
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = tellimused.size() - 1; i > -1; i--) {
            Stage tellitu = new Stage();
            BorderPane paigutus = new BorderPane();
            Button olgu = new Button("Võta");
            olgu.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    tellitu.hide();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            BorderPane.setAlignment(olgu, Pos.CENTER);
            BorderPane.setMargin(olgu, new Insets(12, 12, 12, 12)); // optional
            paigutus.setBottom(olgu);
            Text maksumus = new Text("Tellimus " + tellimused.get(i) + " on valmis!");
            maksumus.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 12));
            BorderPane.setMargin(maksumus, new Insets(12, 12, 12, 12));
            paigutus.setCenter(maksumus);
            Scene stseen2 = new Scene(paigutus);
            tellitu.setScene(stseen2);
            tellitu.show();
        }
    }

    private void tellimuseKüsija(Button enter, TextField sisend, ArrayList<Pitsa> pitsad, ArrayList<Jook> joogid,
                                 ArrayList<Kate> katted) {
        final int[] tellitav = {1};
        final String[] nimi = {""};
        final String[] pitsadeString = {""};
        final String[] jookideString = {""};
        ArrayList<Pitsa> tellitudpitsad = new ArrayList<>();
        ArrayList<Jook> tellitudjoogid = new ArrayList<>();

        enter.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (tellitav[0] == 1) {
                    nimi[0] = sisend.getText();
                    if (!nimi[0].equals("")) {
                        sisend.setText("Sisestage soovitud pitsad...");
                        tellitav[0]++;
                    } else {
                        sisend.setText("Meil on tellimuse jaoks teie nime vaja!");
                    }
                } else if (tellitav[0] == 2) {
                    pitsadeString[0] = sisend.getText();
                    String[] pitsadestring = pitsadeString[0].split(",");
                    boolean sobivad = false;
                    for (int i = 0; i < pitsadestring.length; i++) {
                        for (Pitsa pitsa : pitsad) {
                            if (pitsa.getNimetus().equals(pitsadestring[i].split(" ")[0])) {
                                sobivad = true;
                                break;
                            }
                            sobivad = false;
                        }
                        if (!sobivad) {
                            break;
                        }
                    }
                    boolean suurused = true;
                    for (int i = 0; i < pitsadestring.length; i++) {
                        if (!pitsadestring[i].split(" ")[1].equals("V") &&
                                !pitsadestring[i].split(" ")[1].equals("S")) {
                            suurused = false;
                            break;
                        }
                    }
                    if (sobivad && suurused) {
                        if (!pitsadestring[0].equals("")) {
                            for (String sõne : pitsadestring) {
                                String nimetus = sõne.trim().split(" ")[0];
                                String suurus = sõne.trim().split(" ")[1];
                                for (Pitsa pitsa : pitsad) {
                                    if (nimetus.equals(pitsa.getNimetus()) && suurus.equals(pitsa.getSuurus())) {
                                        tellitudpitsad.add(pitsa);
                                    }
                                }
                            }
                        }
                        sisend.setText("Sisestage soovitud joogid...");
                        tellitav[0]++;
                    } else {
                        sisend.setText("Selliseid pitsasid meil ei müüda, sisestage uuesti...");
                    }

                } else if (tellitav[0] == 3) {
                    jookideString[0] = sisend.getText();
                    String[] jookidestring = jookideString[0].split(",");
                    boolean sobivad = false;
                    for (int i = 0; i < jookidestring.length; i++) {
                        for (Jook jook : joogid) {
                            if (jook.getNimetus().equals(jookidestring[i].split(" ")[0])) {
                                sobivad = true;
                                break;
                            }
                            sobivad = false;
                        }
                        if (!sobivad) {
                            break;
                        }
                    }
                    boolean suurused = true;
                    for (int i = 0; i < jookidestring.length; i++) {
                        if (!jookidestring[i].split(" ")[1].equals("V") &&
                                !jookidestring[i].split(" ")[1].equals("S")) {
                            suurused = false;
                            break;
                        }
                    }
                    if (suurused && sobivad) {
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
                        if (!pitsadeString[0].equals("") || !jookideString[0].equals("")) {
                            Tellimus uus = new Tellimus(nimi[0], tellitudpitsad, tellitudjoogid);
                            try (BufferedWriter tellis = new BufferedWriter(new FileWriter(
                                    new File("tellimused.txt"), true))) {
                                tellis.write(uus.getTellijaNimi() + " " + uus.koguhind() + System.lineSeparator());

                            } catch (IOException e) {
                                System.out.println("Failiga jama");
                            }
                            Stage tellitu = new Stage();
                            // küsimuse ja kahe nupu loomine
                            BorderPane paigutus = new BorderPane();
                            Button olgu = new Button("Okei");
                            olgu.setOnAction(new EventHandler<ActionEvent>() {
                                public void handle(ActionEvent event) {
                                    tellitu.hide();
                                }
                            });
                            BorderPane.setAlignment(olgu, Pos.CENTER);
                            BorderPane.setMargin(olgu, new Insets(12, 12, 12, 12)); // optional
                            paigutus.setBottom(olgu);
                            Text maksumus = new Text("HIND KOKKU: " + uus.koguhind() + " eur");
                            maksumus.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 12));
                            BorderPane.setMargin(maksumus, new Insets(12, 12, 12, 12));
                            paigutus.setCenter(maksumus);
                            Scene stseen2 = new Scene(paigutus);
                            tellitu.setScene(stseen2);
                            tellitu.show();
                            sisend.setText("Tänan! Kui tahate veel midagi tellida, sisestage enda nimi.");
                            tellitav[0] = 1;
                        } else {
                            sisend.setText("Midagi te peate ikka tellima! Sisestage soovitud pitsad...");
                            tellitav[0] = 2;
                        }
                    } else {
                        sisend.setText("Selliseid jooke meil ei müüda, sisestage uuesti...");
                    }
                }
            }
        });
        sisend.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    if (tellitav[0] == 1) {
                        nimi[0] = sisend.getText();
                        if (!nimi[0].equals("")) {
                            sisend.setText("Sisestage soovitud pitsad...");
                            tellitav[0]++;
                        } else {
                            sisend.setText("Meil on tellimuse jaoks teie nime vaja!");
                        }
                    } else if (tellitav[0] == 2) {
                        pitsadeString[0] = sisend.getText();
                        String[] pitsadestring = pitsadeString[0].split(",");
                        boolean sobivad = false;
                        for (int i = 0; i < pitsadestring.length; i++) {
                            for (Pitsa pitsa : pitsad) {
                                if (pitsa.getNimetus().equals(pitsadestring[i].split(" ")[0])) {
                                    sobivad = true;
                                    break;
                                }
                                sobivad = false;
                            }
                            if (!sobivad) {
                                break;
                            }
                        }
                        boolean suurused = true;
                        for (int i = 0; i < pitsadestring.length; i++) {
                            if (!pitsadestring[i].split(" ")[1].equals("V") &&
                                    !pitsadestring[i].split(" ")[1].equals("S")) {
                                suurused = false;
                                break;
                            }
                        }
                        if (sobivad && suurused) {
                            if (!pitsadestring[0].equals("")) {
                                for (String sõne : pitsadestring) {
                                    String nimetus = sõne.trim().split(" ")[0];
                                    String suurus = sõne.trim().split(" ")[1];
                                    for (Pitsa pitsa : pitsad) {
                                        if (nimetus.equals(pitsa.getNimetus()) && suurus.equals(pitsa.getSuurus())) {
                                            tellitudpitsad.add(pitsa);
                                        }
                                    }
                                }
                            }
                            sisend.setText("Sisestage soovitud joogid...");
                            tellitav[0]++;
                        } else {
                            sisend.setText("Selliseid pitsasid meil ei müüda, sisestage uuesti...");
                        }

                    } else if (tellitav[0] == 3) {
                        jookideString[0] = sisend.getText();
                        String[] jookidestring = jookideString[0].split(",");
                        boolean sobivad = false;
                        for (int i = 0; i < jookidestring.length; i++) {
                            for (Jook jook : joogid) {
                                if (jook.getNimetus().equals(jookidestring[i].split(" ")[0])) {
                                    sobivad = true;
                                    break;
                                }
                                sobivad = false;
                            }
                            if (!sobivad) {
                                break;
                            }
                        }
                        boolean suurused = true;
                        for (int i = 0; i < jookidestring.length; i++) {
                            if (!jookidestring[i].split(" ")[1].equals("V") &&
                                    !jookidestring[i].split(" ")[1].equals("S")) {
                                suurused = false;
                                break;
                            }
                        }
                        if (suurused && sobivad) {
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
                            if (!pitsadeString[0].equals("") || !jookideString[0].equals("")) {
                                Tellimus uus = new Tellimus(nimi[0], tellitudpitsad, tellitudjoogid);
                                try (BufferedWriter tellis = new BufferedWriter(new FileWriter(
                                        new File("tellimused.txt"), true))) {
                                    tellis.write(uus.getTellijaNimi() + " " + uus.koguhind() + System.lineSeparator());

                                } catch (IOException e) {
                                    System.out.println("Failiga jama");
                                }
                                Stage tellitu = new Stage();
                                // küsimuse ja kahe nupu loomine
                                BorderPane paigutus = new BorderPane();
                                Button olgu = new Button("Okei");
                                olgu.setOnAction(new EventHandler<ActionEvent>() {
                                    public void handle(ActionEvent event) {
                                        tellitu.hide();
                                    }
                                });
                                BorderPane.setAlignment(olgu, Pos.CENTER);
                                BorderPane.setMargin(olgu, new Insets(12, 12, 12, 12)); // optional
                                paigutus.setBottom(olgu);
                                Text maksumus = new Text("HIND KOKKU: " + uus.koguhind() + " eur");
                                maksumus.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 12));
                                BorderPane.setMargin(maksumus, new Insets(12, 12, 12, 12));
                                paigutus.setCenter(maksumus);
                                Scene stseen2 = new Scene(paigutus);
                                tellitu.setScene(stseen2);
                                tellitu.show();
                                sisend.setText("Tänan! Kui tahate veel midagi tellida, sisestage enda nimi.");
                                tellitav[0] = 1;
                            } else {
                                sisend.setText("Midagi te peate ikka tellima! Sisestage soovitud pitsad...");
                                tellitav[0] = 2;
                            }
                        } else {
                            sisend.setText("Selliseid jooke meil ei müüda, sisestage uuesti...");
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
}