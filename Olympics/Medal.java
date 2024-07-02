package Olympics;

public class Medal {
    public enum Type{
        BRONZE,
        SILVER,
        GOLD
    }
    private String tournament;
    private int year;
    private Type type;

    public Medal(Type type, String tournament, int year) {
        this.type = type;
        this.tournament = tournament;
        this.year = year;
    }
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
