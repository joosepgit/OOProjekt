package Rühmatöö;

import java.util.ArrayList;

public class Tellimus {
    private String tellijaNimi;
    private ArrayList<Pitsa> pitsad;
    private ArrayList<Jook> joogid;
    private double koguhind;

    public Tellimus(String tellijaNimi, ArrayList<Pitsa> pitsad, ArrayList<Jook> joogid, double koguhind){
        this.tellijaNimi = tellijaNimi;
        this.pitsad = pitsad;
        this.joogid = joogid;
        this.koguhind = koguhind;
    }

    public String getTellijaNimi() {
        return tellijaNimi;
    }

    public void setTellijaNimi(String tellijaNimi) {
        this.tellijaNimi = tellijaNimi;
    }

    public ArrayList<Pitsa> getPitsad() {
        return pitsad;
    }

    public void setPitsad(ArrayList<Pitsa> pitsad) {
        this.pitsad = pitsad;
    }

    public ArrayList<Jook> getJoogid() {
        return joogid;
    }

    public void setJoogid(ArrayList<Jook> joogid) {
        this.joogid = joogid;
    }

    public double getKoguhind() {
        return koguhind;
    }

    public void setKoguhind(double koguhind) {
        this.koguhind = koguhind;
    }

    @Override
    public String toString() {
        return "Tellimus{" +
                "tellijaNimi='" + tellijaNimi + '\'' +
                ", pitsad=" + pitsad +
                ", joogid=" + joogid +
                ", koguhind=" + koguhind +
                '}';
    }
}
