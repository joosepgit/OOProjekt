package Rühmatöö;

public class Jook implements Suurus{
    private String nimetus;
    private double hind;
    private double suurus;

    public Jook(String nimetus, double hind, double suurus){
        this.nimetus = nimetus;
        this.hind = hind;
        this.suurus = suurus;
    }

    public String getNimetus() {
        return nimetus;
    }

    public void setNimetus(String nimetus) {
        this.nimetus = nimetus;
    }

    public double getHind() {
        return hind;
    }

    public void setHind(double hind) {
        this.hind = hind;
    }

    public double getSuurus() {
        return suurus;
    }

    public void setSuurus(double suurus) {
        this.suurus = suurus;
    }

    @Override
    public String toString() {
        return "Jook{" +
                "nimetus='" + nimetus + '\'' +
                ", hind=" + hind +
                ", suurus=" + suurus +
                '}';
    }
}
