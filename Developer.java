import java.util.*;

public class Developer extends Employee {
    protected String teamName;
    protected ArrayList<String> programmingLanguages;
    protected int expYear;

    public Developer(String empID, String empName, int baseSal, String teamName, ArrayList<String> programmingLanguages,
            int expYear) {
        super(empID, empName, baseSal);
        this.teamName = teamName;
        this.programmingLanguages = programmingLanguages;
        this.expYear = expYear;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public ArrayList<String> getProgrammingLanguages() {
        return this.programmingLanguages;
    }

    public int getExpYear() {
        return this.expYear;
    }

    @Override
    public double getSalary() {
        if (this.expYear >= 5) {
            return super.baseSal + this.expYear * 2000000;
        } else if (this.expYear >= 3) {
            return super.baseSal + this.expYear * 1000000;
        }
        return super.baseSal;
    }

    @Override
    public String toString() {
        return super.toString() + "_" + this.teamName + "_" + this.programmingLanguages + "_" + this.expYear;
    }
}
