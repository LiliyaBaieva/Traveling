import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Calculator {

  public static double appartCalc(int numberPeople, int days) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    double cost;
    System.out.println("Do you know the cost of living per night for 1 person?");
    boolean answer = readYesNo();
    if (answer) {
      System.out.println("Enter the cost of accommodation per night for 1 person?");
      double oneDay = 0.00;
      while(oneDay < 0){
        oneDay = Double.parseDouble(Trip.readLine());
      }
      cost = oneDay * days;
    } else {
      System.out.print("Enter the cost of accommodation per day: ");
      cost = Double.parseDouble(Trip.readLine()) / numberPeople * days;
    }
    return cost;
  }

  public static double transferCalc(int numberPeople) {
    double cost = 0.00;
    System.out.println("Will you be traveling by car?");
    boolean answer = readYesNo();
    if (answer) {
      if (numberPeople <= 5) {
        cost = goByCar(numberPeople);
      } else {
        System.out.println("More than 5 people cannot travel by car.");
      }
    } else {
      System.out.println("Enter the cost of a one-way ticket: ");
      double ticket = 0.00;
      try {
        ticket = Double.parseDouble(Trip.readLine());
        return ticket;
      } catch (NumberFormatException e) {
        System.out.println("Invalid input format.");
        transferCalc(numberPeople);
      }
      cost = ticket * 2;
    }
    return cost;
  }

  public static double localTransportCalc() {
    double localTransportCost = 0.00;
    System.out.println("Do you plan to use local transport?");
    boolean answer = readYesNo();
    if (answer) {
      System.out.println(
          "Enter the amount of daily travel on public transport per person: ");
      try {
        double busTicket = Double.parseDouble(Trip.readLine());
        System.out.println(
            "How many days do you plan to use public transport?");
        int busDays = Integer.parseInt(Trip.readLine());
        localTransportCost = busTicket * busDays;
      } catch (NumberFormatException e) {
        System.out.println("Invalid input format.");
        localTransportCalc();
      }
    }
    return localTransportCost;
  }

  public static double foodCalc(int days) {
    int answer;
    do {
      System.out.println("""
          Select your holiday meal plan:
           [1] - eat in cafes and restaurants
           [2] - we cook ourselves
           [3] - mixed nutrition
          """);
      answer = Integer.parseInt(Trip.readLine());
    } while (!(answer == 1 || answer == 2 || answer == 3));
    return foodCalc2(answer, days);
  }

  private static double foodCalc2(int answer, int days) {
    double cost = 0.00;
    try {
      switch (answer) {
        case 1 -> {
          System.out.println("Enter the average cost of lunch: ");
          cost = Double.parseDouble(Trip.readLine()) * 3 * days;
        }
        case 2 -> {
          System.out.println("What is the cost of weekly food purchases (per person): ");
          // предполагаеться, что цены могут быть выше, запас денег на 10% больше
          cost = Double.parseDouble(Trip.readLine()) / 7 * 1.1 * days;
        }
        case 3 -> {
          System.out.println("Enter the average cost of lunch: ");
          double dinnerCost = Double.parseDouble(Trip.readLine());
          System.out.println("What is the cost of weekly food purchases (per person): ");
          double shopFood = Double.parseDouble(Trip.readLine());
          cost = ((dinnerCost * 3) + (shopFood / 7) * 1.1) / 2 * days;
        }
      }
    } catch (NumberFormatException e) {
      System.out.println("Invalid input format.");
      foodCalc2(answer, days);
    } catch (NegativeArraySizeException e) {
      System.out.println("The amount of money cannot be negative.");
      foodCalc2(answer, days);
    }
    return cost;
  }

  public static double excursionCalc() {
    double costOne;
    double cost = 0.00;
    try {
      System.out.println("Enter the average cost of the excursion per person: ");
      costOne = Double.parseDouble(Trip.readLine());
      System.out.println("Enter the number of excursions: ");
      cost = Double.parseDouble(Trip.readLine()) * costOne;
    } catch (NumberFormatException e) {
      System.out.println("Invalid input format");
      excursionCalc();
    } catch (NegativeArraySizeException e) {
      System.out.println("Cost cannot be negative");
      excursionCalc();
    }
    return cost;
  }

  public static double entertainmentCalk() {
    double entertainmentCost = 0.00;
    System.out.println("Are you planning additional expenses for entertainment?");
    boolean answer = readYesNo();
    if (answer) {
      System.out.println(
          "How much do you plan to spend on entertainment and additional purchases: ");
      entertainmentCost = Double.parseDouble(Trip.readLine());
    }
    return entertainmentCost;
  }

  public static double goByCar(int numberPeople) {

    System.out.println("How many km one way: ");
    double distance;
    do {
      distance = Double.parseDouble(Trip.readLine());
    } while (distance < 0);

    System.out.println("What is the fuel consumption of your car?");
    double fuelConsumption;
    do {
      fuelConsumption = Double.parseDouble(Trip.readLine());
    } while (fuelConsumption < 0);

    System.out.println("Average cost of 1 liter of fuel: ");
    double fuelCost;
    do {
      fuelCost = Double.parseDouble(Trip.readLine());
    } while (fuelCost < 0);

    System.out.println("Will there be toll roads along the route?");
    double autobahn = 0.00;
    boolean answer = readYesNo();
    if (answer) {
      System.out.println("Enter the total cost of a round-trip highway trip.");
      do {
        autobahn = Double.parseDouble(Trip.readLine());
      } while (autobahn < 0);
    }
    return ((fuelConsumption / 100 * fuelCost) * distance * 2 + autobahn) / numberPeople;
  }

  public static boolean readYesNo() {
    System.out.println("[y] - yes\n[n] - no");
    String answerLine = null;

    while(answerLine.equals("y") || answerLine.equals("n")){
        answerLine = Trip.readLine().toLowerCase();
    }

    if (answerLine.equals("n")) {
      return false;
    } else if (!answerLine.equals("y")) {
      System.out.println("Invalid answer input.");
      readYesNo();
    }
    return true;
  }

  public static Double totalOneCalc(Double accommodation, Double transfer, Double localTransport,
      Double food, Double excursions, Double entertainment) {
    return accommodation + transfer + localTransport + food + excursions + entertainment;
  }

  public static Double totalAllCalc(Double totalOne, Integer people) {
    return totalOne * people;
  }
}
