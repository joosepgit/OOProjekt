package Rühmatöö;

public class Jook {
    private String nimetus;
    private double hind;
    private String suurus;
    private double väiksehind;

    public Jook(String nimetus, String suurus, double hind){
        this.nimetus = nimetus;
        this.suurus = suurus;
        this.hind = hind;
        this.väiksehind = hind /2.0;
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
        return "\n\n" + nimetus + "\nHind (suur/väike): " + hind  + "/" + väiksehind;
    }
    public String üksik(){
        double selleHind = 0;
        if(suurus.equals("S")){
            selleHind = hind;
        }
        else{
            selleHind = väiksehind;
        }
        return "\n\n" + nimetus + "\nHind: " + selleHind;
    }
}
