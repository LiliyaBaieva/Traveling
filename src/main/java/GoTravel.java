import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GoTravel {

  private TripManager tripManager = new TripManager();

  public void go() {

    for (int i = 0; i < menu().size(); ++i) {
      System.out.println("[" + (i + 1) + "] " + menu().get(i));
    }

    int choice = readChoice();
    switch (choice) {
      case 1 -> new Trip().create();
      case 2 -> tripManager.printTrips();
//      case 3 -> TripManager.editTrip();
//      case 4 -> aboutApp();
//      case 5 -> exit();
    }


  }

  private static List<String> menu() {
    List<String> menu = new ArrayList<>();
    menu.add("Create a trip"); //1
    menu.add("View all trip");  //2
    menu.add("Edit a trip");
    menu.add("About APP");
    menu.add("Quit"); //7
    return menu;
  }


  private static int readChoice() {

    //  Считываем выбор меню от пользователя
    System.out.println("-----------------------------");
    System.out.print("    Make your choice: ");
    int choice = 0;
    try {
      choice = Integer.parseInt(Trip.readLine());
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid input: \"" + e + "\"");
//      go();
    }
    if (choice > 5) {
      System.out.println("Enter from 1 to 5");
//      go();
    }
    System.out.println();
    return choice;
  }

  private static void aboutApp() {
    System.out.println("""
                        >> TRAVEL COST <<
             This program was created by a man who loves to travel.
             Designed to quickly calculate your trip and optimize your expenses.
        """);
  }

  private static void exit() {
    System.out.println("""
           We wish you a good trip! 
                  Goodbye!
        """);
  }


}
