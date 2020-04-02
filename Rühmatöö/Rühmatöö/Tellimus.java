package Rühmatöö;

import java.util.ArrayList;

public class Tellimus {
    private String tellijaNimi;
    private ArrayList<Pitsa> pitsad;
    private ArrayList<Jook> joogid;

    public Tellimus(String tellijaNimi, ArrayList<Pitsa> pitsad, ArrayList<Jook> joogid){
        this.tellijaNimi = tellijaNimi;
        this.pitsad = pitsad;
        this.joogid = joogid;
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

    @Override
    public String toString() {
        return "TELLIMUS\n" +
                "Kellele: " + tellijaNimi +
                " \nPitsad:" + pitsad +
                " \n\nJoogid:" + joogid;
    }
    public double koguhind(){
        double hind = 0;
        for (int i = 0; i < pitsad.size(); i++) {
            if(pitsad.get(i).getSuurus().equals("S")){
                hind += pitsad.get(i).getSuureHind();
            }
            else{
                hind += pitsad.get(i).getVäikseHind();
            }
        }
        for (int i = 0; i < joogid.size(); i++) {
            hind += joogid.get(i).getHind();
        }
        return hind;
    }
}
