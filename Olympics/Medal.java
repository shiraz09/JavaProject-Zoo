/** Shiraz Nagaoker 208324194
 Topaz Natan 311561567  **/

package Olympics;

/** Defining the medal class **/
public class Medal {
    public enum Type{
        BRONZE,
        SILVER,
        GOLD
    }

    /** private variables **/
    private String tournament;
    private int year;
    private Type type;

    /** The constructor of Medal **/
    public Medal(Type type, String tournament, int year) {
        this.type = type;
        this.tournament = tournament;
        this.year = year;
    }

    /** access method getType **/
    public Type getType() {
        return type;
    }

    /** setType setter method **/
    public void setType(Type type) {
        this.type = type;
    }

    /** access method getTournament **/
    public String getTournament() {
        return tournament;
    }

    /** setTournament setter method **/
    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    /** access method getYear **/
    public int getYear() {
        return year;
    }

    /** setYear setter method **/
    public void setYear(int year) {
        this.year = year;
    }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Medal medal = (Medal) obj;

        if (year != medal.year) return false;
        if (type != medal.type) return false;
        return tournament != null ? tournament.equals(medal.tournament) : medal.tournament == null;
    }

    public String toString() {
        return "Medal{" +
                "type=" + type +
                ", tournament='" + tournament + '\'' +
                ", year=" + year +
                '}';
    }
}