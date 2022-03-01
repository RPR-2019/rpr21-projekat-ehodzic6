package ba.unsa.etf.rpr;

public class NalazPsihijatra {
    private boolean agresija,piromanija,depresija,ptsd,sizofrenija,alkohol,psihoaktivne_supstance,psihopatija,podvojena_licnost,manipulacije;

    public NalazPsihijatra(boolean agresija, boolean piromanija, boolean depresija, boolean ptsd, boolean sizofrenija, boolean alkohol, boolean psihoaktivne_supstance, boolean psihopatija, boolean podvojena_licnost, boolean manipulacije) {
        this.agresija = agresija;
        this.piromanija = piromanija;
        this.depresija = depresija;
        this.ptsd = ptsd;
        this.sizofrenija = sizofrenija;
        this.alkohol = alkohol;
        this.psihoaktivne_supstance = psihoaktivne_supstance;
        this.psihopatija = psihopatija;
        this.podvojena_licnost = podvojena_licnost;
        this.manipulacije = manipulacije;
    }

    public boolean isAgresija() {
        return agresija;
    }

    public void setAgresija(boolean agresija) {
        this.agresija = agresija;
    }

    public boolean isPiromanija() {
        return piromanija;
    }

    public void setPiromanija(boolean piromanija) {
        this.piromanija = piromanija;
    }

    public boolean isDepresija() {
        return depresija;
    }

    public void setDepresija(boolean depresija) {
        this.depresija = depresija;
    }

    public boolean isPtsd() {
        return ptsd;
    }

    public void setPtsd(boolean ptsd) {
        this.ptsd = ptsd;
    }

    public boolean isSizofrenija() {
        return sizofrenija;
    }

    public void setSizofrenija(boolean sizofrenija) {
        this.sizofrenija = sizofrenija;
    }


    public boolean isAlkohol() {
        return alkohol;
    }

    public void setAlkohol(boolean alkohol) {
        this.alkohol = alkohol;
    }

    public boolean isPsihoaktivne_supstance() {
        return psihoaktivne_supstance;
    }

    public void setPsihoaktivne_supstance(boolean psihoaktivne_supstance) {
        this.psihoaktivne_supstance = psihoaktivne_supstance;
    }

    public boolean isPsihopatija() {
        return psihopatija;
    }

    public void setPsihopatija(boolean psihopatija) {
        this.psihopatija = psihopatija;
    }

    public boolean isPodvojena_licnost() {
        return podvojena_licnost;
    }

    public void setPodvojena_licnost(boolean podvojena_licnost) {
        this.podvojena_licnost = podvojena_licnost;
    }

    public boolean isManipulacije() {
        return manipulacije;
    }

    public void setManipulacije(boolean manipulacije) {
        this.manipulacije = manipulacije;
    }
}
