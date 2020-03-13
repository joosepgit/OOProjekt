import java.util.ArrayList;
package Rühmatöö;

public class Menüü {
    private ArrayList<Jook> joogid;
    private ArrayList<Pitsa> pitsad;

    public ArrayList<Jook> getJoogid() {
        return joogid;
    }

    public void setJoogid(ArrayList<Jook> joogid) {
        this.joogid = joogid;
    }

    public ArrayList<Pitsa> getPitsad() {
        return pitsad;
    }

    public void setPitsad(ArrayList<Pitsa> pitsad) {
        this.pitsad = pitsad;
    }

    public Menüü(){
        this.joogid = new ArrayList<Jook>;
        this.pitsad = new ArrayList<Pitsa>;
    }

    @Override
    public String toString() {
        return "Menüü{" +
                "joogid=" + joogid +
                ", pitsad=" + pitsad +
                '}';
    }
}
