import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TripManager {

  public final static char SEP = '/';

  public static void printTrip() throws IOException {
    String name = getTripName();
    int numberOfPeople = getNumberOfPeople();
    for (int i = 0; i < name.length() + 6; ++i) {
      System.out.print("=");
    }
    System.out.println("\n|| " + name.toUpperCase() + " ||");
    for (int i = 0; i < name.length() + 6; ++i) {
      System.out.print("=");
    }
    System.out.println();

    List<Expense> tripList = parseTripList();
    for (int i = 0; i < tripList.size(); ++i) {
      Expense expense = tripList.get(i);
      if (i == tripList.size() - 1) {
        System.out.println("=============================================================");
        double costOnePerson = tripList.get(tripList.size() - 1).getCost();
        String currency = tripList.get(tripList.size() - 1).getCurrency();
        double cosAllpeople = costOnePerson * numberOfPeople;
        System.out.printf("The amount of your trip will be: per 1 person = %.2f (%s)\n",
            costOnePerson, currency);
        System.out.printf("                               per company   = %.2f (%s)\n",
            cosAllpeople, currency);

      } else {
        System.out.printf("[%d] %s \n", i + 1, expense);
      }
    }

    System.out.println();

    GoTravel.runMenu();
  }

  public static void editTrip() throws IOException {
    String nameOfTrip = getTripName();
    int numberPeople = TripCreator.numberPeople;

    System.out.println("Select the expense item you want to edit : ");
    System.out.println("_______________________________________________________________");
    List<Expense> tripList = parseTripList();
    for (int i = 0; i < tripList.size() - 1; ++i) {
      System.out.printf("[%d] %s\n", i + 1, tripList.get(i).getName());
    }
    System.out.println();
    System.out.println("Make your choice: ");

    int choice = choiceForEdit();
    double newCost = costCalc(choice);
    tripList.get(choice - 1).setCost(newCost);

    File trips = new File("res/trips.txt");

    FileWriter fileWriter = new FileWriter("res/trips.txt");
    if (!trips.exists()) {
      System.out.println("First, create a trip.");
      GoTravel.runMenu();
    }

    String allExpenses = "/";
    for (Expense expense : tripList) {
      String name = expense.getName();
      double cost = expense.getCost();
      String currency = expense.getCurrency();
      String writeExpense = String.format("%s;%.2f;%s", name, cost, currency);
      allExpenses = allExpenses + writeExpense + SEP;
    }
    String total = String.format(nameOfTrip + ";" + numberPeople + allExpenses + "\n");
    fileWriter.write(String.valueOf(total));
    fileWriter.close();

    GoTravel.runMenu();
  }

  public static int choiceForEdit() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int choice = 0;
    try {
      do {
        choice = Integer.parseInt(br.readLine());
      } while (!(choice > 0 && choice <= 6));
    } catch (NumberFormatException e) {
      System.out.println("Invalid input");
      editTrip();
    }
    return choice;
  }

  private static String getTripName() throws IOException {

  }

  private static int getNumberOfPeople() throws IOException {
    File tripsFile = new File("res/Trips.txt");
    if (!tripsFile.exists()) {
      System.out.println("File not found. Or you haven’t created more than one trip yet.");
      GoTravel.runMenu();
    }
    BufferedReader br = new BufferedReader(new FileReader("res/Trips.txt"));

    String line = br.readLine();
    int sepLeft = line.indexOf(";");
    int sepRight = line.indexOf(SEP);
    return Integer.parseInt(line.substring(sepLeft + 1, sepRight));
  }

  public static List<Expense> parseTripList() throws IOException {
    File tripsFile = new File("res/Trips.txt");
    if (!tripsFile.exists()) {
      System.out.println("File not found. Or you haven’t created more than one trip yet.");
      GoTravel.runMenu();
    }
    BufferedReader br = new BufferedReader(new FileReader("res/Trips.txt"));

    String line = br.readLine();
    int firstSep = line.indexOf(SEP);

    String lineForList = line.substring(firstSep + 1);
    List<Expense> expenses = new ArrayList<>();
    while (lineForList.length() != 0) {
      int lastSep = lineForList.indexOf(SEP);
      String lineForeExp = lineForList.substring(0, lastSep);
      Expense expense = parseExpenses(lineForeExp);
      expenses.add(expense);
      lineForList = lineForList.substring(lastSep + 1);
    }
    br.close();
    return expenses;
  }


  private static Expense parseExpenses(String lineForeExp) {
    char sep = ';';
    int firstSep = lineForeExp.indexOf(sep);
    int secondSep = lineForeExp.indexOf(sep, firstSep + 1);

    String name = lineForeExp.substring(0, firstSep);

    String costString = lineForeExp.substring(firstSep + 1, secondSep);
    double cost;
    if (costString.contains(",")) {
      String newcostToString = costString.replace(',', '.');
      cost = Double.parseDouble(newcostToString);
    } else {
      cost = Double.parseDouble(costString);
    }

    String currency = lineForeExp.substring(secondSep + 1);
    return new Expense(name, cost, currency);
  }

}

