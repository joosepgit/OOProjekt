package Rühmatöö;

import java.util.ArrayList;
import java.util.List;

public class Menüü {
    private List<Jook> joogid;
    private List<Pitsa> pitsad;

    public List<Jook> getJoogid() {
        return joogid;
    }

    public void setJoogid(List<Jook> joogid) {
        this.joogid = joogid;
    }

    public List<Pitsa> getPitsad() {
        return pitsad;
    }

    public void setPitsad(List<Pitsa> pitsad) {
        this.pitsad = pitsad;
    }

    public Menüü(){
        this.joogid = new ArrayList<Jook>();
        this.pitsad = new ArrayList<Pitsa>();
    }

    @Override
    public String toString() {
        return "MENÜÜ\n\n" +
                "PITSAD" + pitsad.toString().substring(1,pitsad.toString().length()-1) +
        "\n\nPitsale 'Omalooming' saab valida kuni 4 lisakomponenti! \n\nJOOGID" +
                joogid.toString().substring(1,joogid.toString().length()-1)
                + "\n\nMENÜÜ LÕPP\n";
    }
}
