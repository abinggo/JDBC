package jdbcd;

public class Score {
    private int id;
    private String name;
    private int math;
    private int english;
    private int chniese;

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", math=" + math +
                ", english=" + english +
                ", chniese=" + chniese +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getChniese() {
        return chniese;
    }

    public void setChniese(int chniese) {
        this.chniese = chniese;
    }
}
