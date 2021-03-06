package Rühmatöö;
import java.util.ArrayList;

public class Pitsa{
    private ArrayList<Kate> katted;
    private String suurus;
    private String nimetus;
    private double väiksehind;
    private double suurehind;

    public Pitsa(ArrayList<Kate> katted, String suurus, String nimetus){
        this.katted = katted;
        this.suurus = suurus;
        this.nimetus = nimetus;
    }

    public ArrayList<Kate> getKatted() {
        return katted;
    }

    public void setKatted(ArrayList<Kate> katted) {
        this.katted = katted;
    }
    public double getHind(){
        if (suurus.equals("V".toLowerCase()))
            return getVäikseHind();
        return getSuureHind();
    }

    public double getVäikseHind() {
        double hind = 0;
        for (Kate kate : katted)
            hind+= kate.getHind();
        return Math.ceil(hind*6)/10.0;
    }
    public double getSuureHind() {
        double hind = 0;
        for (Kate kate : katted)
            hind+= kate.getHind();
        return hind;
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

    public void lisaKate(Kate midagi){
        katted.add(midagi);
    }

    @Override
    public String toString() {
        return "\n\nPitsa: " + nimetus +
                "\nKoostisosad: " + katted.toString().substring(1,katted.toString().length()-1) +
                "\nHind(väike/suur): " + getVäikseHind()+"/"+getSuureHind();

    }
    public String üksik(){
        double selleHind = 0;
        if(suurus.equals("S")){
            selleHind = getSuureHind();
        }
        else{
            selleHind = getVäikseHind();
        }
        return "\n\nPitsa: " + nimetus +
                "\nKoostisosad: " + katted.toString().substring(1,katted.toString().length()-1) +
                "\nHind: " + selleHind;
    }
}
