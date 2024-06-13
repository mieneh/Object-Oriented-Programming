import java.util.ArrayList;

public class TeamLeader extends Developer {
    private double bonus_rate;

    public TeamLeader(String empID, String empName, int basicSal, String teamName,
            ArrayList<String> programmingLanguages, int expYear, double bonus_rate) {
        super(empID, empName, basicSal, teamName, programmingLanguages, expYear);
        this.bonus_rate = bonus_rate;
    }

    public double getBonus_rate() {
        return this.bonus_rate;
    }

    @Override
    public double getSalary() {
        return super.getSalary() + this.bonus_rate * super.getSalary();
    }
}
