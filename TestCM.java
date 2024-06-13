import java.util.*;
import java.io.*;

public class TestCM {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws FileNotFoundException {
        // Requirement 1
        CompanyManagement icm = new CompanyManagement(".//input//ListOfEmployees.txt", "input/PLInfo.txt");
        icm.printEmpList();

        // Requirement 2
        ArrayList<Developer> Req2 = icm.getDeveloperByProgrammingLanguage("C++");
        icm.writeFile(".//output//Req2.txt", Req2);

        // Requirement 3
        ArrayList<Tester> Req3 = icm.getTestersHaveSalaryGreaterThan(4700000);
        icm.writeFile(".//output//Req3.txt", Req3);

        // Requirement 4
        Employee Req4 = icm.getEmployeeWithHigestSalary();
        icm.writeFile(".//output//Req4.txt", Req4);

        // Requirement 5
        TeamLeader Req5 = icm.getLeaderWithMostEmployees();
        icm.writeFile(".//output//Req5.txt", Req5);

        // Requirement 6
        ArrayList<Employee> Req6 = icm.sorted();
        icm.writeFile(".//output//Req6.txt", Req6);
    }
}