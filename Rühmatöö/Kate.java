package Rühmatöö;

public class Kate {
    private String nimetus;
    private double hind;

    public Kate(String nimetus, double hind){
        this.nimetus = nimetus;
        this.hind = hind;
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

    @Override
    public String toString() {
        return nimetus;
    }
}
