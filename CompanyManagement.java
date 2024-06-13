import java.util.*;
import java.io.*;

class MySort implements Comparator<Employee> {
  @Override
  public int compare(Employee a, Employee b) {
    if (a.getSalary() == b.getSalary()) {
      String nameA = a.getEmpName().split(" ")[a.getEmpName().split(" ").length - 1];
      String nameB = b.getEmpName().split(" ")[b.getEmpName().split(" ").length - 1];
      return nameA.charAt(0) > nameB.charAt(0) ? 1 : -1;
    }
    return a.getSalary() < b.getSalary() ? 1 : -1;
  }
}

public class CompanyManagement<E> {
  private ArrayList<Employee> empList;

  // path: path of ListOfEmployees, path1: path of PLInfo.txt
  public CompanyManagement(String path, String path1) throws FileNotFoundException {
    empList = getEmployeeFromFile(path, path1);
  }

  // Phan code cua sinh vien

  // path: path of ListOfEmployees, path1: path of PLInfo.txt
  public ArrayList<Employee> getEmployeeFromFile(String path, String path1) throws FileNotFoundException {
    ArrayList<String> lgDev = new ArrayList<String>();
    ArrayList<Employee> result = new ArrayList<Employee>();
    File file = new File(path1);
    Scanner sc = new Scanner(file);

    while (sc.hasNextLine()) {
      String lg = sc.nextLine();
      lgDev.add(lg);
    }
    sc.close();
    file = new File(path);
    sc = new Scanner(file);
    while (sc.hasNextLine()) {
      String[] tmp = sc.nextLine().split(",");
      if (tmp[1].charAt(0) == 'T') {
        Tester tester = new Tester(tmp[1], tmp[2], Integer.valueOf(tmp[5]), Double.valueOf(tmp[3]), tmp[4]);
        result.add(tester);
      } else if (tmp.length == 6) {
        ArrayList<String> programLanguages = new ArrayList<String>();
        for (String lg : lgDev) {
          if (lg.split(",")[0].equals(tmp[1])) {
            for (int i = 1; i < lg.split(",").length; i++) {
              programLanguages.add(lg.split(",")[i]);
            }
            break;
          }
        }
        Developer developer = new Developer(tmp[1], tmp[2], Integer.valueOf(tmp[5]), tmp[3], programLanguages,
            Integer.valueOf(tmp[4]));
        result.add(developer);
      } else {
        ArrayList<String> programLanguages = new ArrayList<String>();
        for (String lg : lgDev) {
          if (lg.split(",")[0].equals(tmp[1])) {
            for (int i = 1; i < lg.split(",").length; i++) {
              programLanguages.add(lg.split(",")[i]);
            }
            break;
          }
        }
        TeamLeader leader = new TeamLeader(tmp[1], tmp[2], Integer.valueOf(tmp[7]), tmp[3], programLanguages,
            Integer.valueOf(tmp[4]), Double.valueOf(tmp[6]));
        result.add(leader);
      }
    }
    sc.close();
    return result;
  }

  public ArrayList<Developer> getDeveloperByProgrammingLanguage(String pl) {
    ArrayList<Developer> result = new ArrayList<Developer>();
    for (Employee employee : empList) {
      if (employee instanceof Developer) {
        Developer developer = (Developer) employee;
        if (developer.getProgrammingLanguages().contains(pl)) {
          result.add(developer);
        }
      }
    }
    return result;
  }

  public ArrayList<Tester> getTestersHaveSalaryGreaterThan(double value) {
    ArrayList<Tester> result = new ArrayList<Tester>();
    for (Employee employee : empList) {
      if (employee instanceof Tester) {
        Tester tester = (Tester) employee;
        if (tester.getSalary() > value) {
          result.add(tester);
        }
      }
    }
    return result;
  }

  public Employee getEmployeeWithHigestSalary() {
    int position = 0;
    for (int i = 0; i < empList.size(); i++) {
      if (empList.get(i).getSalary() >= empList.get(position).getSalary()) {
        position = i;
      }
    }

    return empList.get(position);
  }

  public TeamLeader getLeaderWithMostEmployees() {
    HashSet<String> groups = new HashSet<String>();
    HashMap<String, Integer> countGroups = new HashMap<String, Integer>();
    for (Employee employee : empList) {
      if (employee instanceof TeamLeader) {
        groups.add(((TeamLeader) employee).getTeamName());
      }
    }

    System.out.println(groups);

    for (String group : groups) {
      int countGroup = 0;
      for (Employee employee : empList) {
        if (employee instanceof Developer) {
          Developer developer = (Developer) employee;
          if (developer.getTeamName().equals(group)) {
            countGroup += 1;
          }
        }
      }
      countGroups.put(group, countGroup);
    }

    String nameGroup = "";
    int sl = 0;
    for (String key : countGroups.keySet()) {
      if (countGroups.get(key) > sl) {
        nameGroup = key;
        sl = countGroups.get(key);
      }
    }

    for (Employee employee : empList) {
      if (employee instanceof TeamLeader && ((TeamLeader) employee).getTeamName().equals(nameGroup)) {
        return (TeamLeader) employee;
      }
    }
    return null;
  }

  public ArrayList<Employee> sorted() {
    ArrayList<Employee> result = empList;
    Collections.sort(result, new MySort());
    for (Employee employee : result) {
      System.out.println(employee.getEmpID() + " " + employee.getSalary());
    }
    return result;
  }

  public void printEmpList() {
    for (Employee tmp : empList) {
      System.out.println(tmp);
    }
  }

  public <E> boolean writeFile(String path, ArrayList<E> list) {
    try {
      FileWriter writer = new FileWriter(path);
      for (E tmp : list) {
        writer.write(tmp.toString());
        writer.write("\r\n");
      }
      writer.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("Error.");
      return false;
    }
    return true;
  }

  public <E> boolean writeFile(String path, E object) {
    try {
      FileWriter writer = new FileWriter(path);
      writer.write(object.toString());
      writer.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("Error.");
      return false;
    }
    return true;
  }
}