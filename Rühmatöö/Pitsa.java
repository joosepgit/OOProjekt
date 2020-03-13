package Rühmatöö;
import java.util.ArrayList;

public class Pitsa implements Suurus{
    private ArrayList<Kate> katted;
    private double hind;
    private String suurus;
    private String nimetus;

    public Pitsa(ArrayList<Kate> katted, double hind, String suurus, String nimetus){
        this.katted = katted;
        this.hind = hind;
        this.suurus = suurus;
        this.nimetus = nimetus;
    }

    public ArrayList<Kate> getKatted() {
        return katted;
    }

    public void setKatted(ArrayList<Kate> katted) {
        this.katted = katted;
    }

    public double getHind() {
        return hind;
    }

    public void setHind(double hind) {
        this.hind = hind;
    }

    public String getSuurus() {
        return suurus;
    }

    public void setSuurus(String suurus) {
        this.suurus = suurus;
    }

    public String getNimetus() {
        return nimetus;
    }

    public void setNimetus(String nimetus) {
        this.nimetus = nimetus;
    }

    @Override
    public String toString() {
        return "Pitsa{" +
                "katted=" + katted +
                ", hind=" + hind +
                ", suurus='" + suurus + '\'' +
                ", nimetus='" + nimetus + '\'' +
                '}';
    }
}
