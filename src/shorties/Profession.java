package shorties;

public abstract class Profession {
    private Shorty shorty;

    public Profession() {
        shorty = null;
    }

    public boolean setShorty(Shorty shorty) {
        if (this.shorty == null) {
            this.shorty = shorty;
            return true;
        }
        return false;
    }

    public Shorty getShorty() {
        return shorty;
    }

    public String getName() {
        return shorty == null ? getProfessionName() : shorty.name;
    }

    public boolean isMan() {
        return (shorty == null) || shorty.isMan;
    }

    public abstract String getProfessionName();

    /*public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }*/
}
