package Rühmatöö;

public class Jook implements Suurus{
    private String nimetus;
    private double hind;
    private String suurus;

    public Jook(String nimetus, String suurus, double hind){
        this.nimetus = nimetus;
        this.suurus = suurus;
        this.hind = hind;
    }

    public double getHind() {
        return hind;
    }

    public void setHind(double hind) {
        this.hind = hind;
    }

    public String getNimetus() {
        return nimetus;
    }

    public void setNimetus(String nimetus) {
        this.nimetus = nimetus;
    }

    public String getSuurus() {
        return suurus;
    }

    public void setSuurus(String suurus) {
        this.suurus = suurus;
    }

    @Override
    public String toString() {
        return "\n\n" + nimetus + ", Hind(väike/suur): " + hind;
    }

    @Override
    public void valiSuurus() {
    }
}
