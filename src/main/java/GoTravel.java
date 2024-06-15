import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GoTravel {

  public static void runMenu() throws IOException {

    for (int i = 0; i < menu().size(); ++i) {
      System.out.println("[" + (i + 1) + "] " + menu().get(i));
    }

    int choice = readChoice();
    switch (choice) {
      case 1 -> Trip.create();
      case 2 -> TripManager.printTrip();
//      case 2 -> printTrips();
      case 3 -> TripManager.editTrip();
      case 4 -> aboutApp();
      case 5 -> exit();
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


  private static int readChoice() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    //  Считываем выбор меню от пользователя

    System.out.println("-----------------------------");
    System.out.print("    Make your choice: ");
    int readedChoice = 0;
    try {
      readedChoice = Integer.parseInt(br.readLine());
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid input: \"" + e + "\"");
      runMenu();
    }
    if (readedChoice > 5) {
      System.out.println("Enter from 1 to 5");
      runMenu();
    }
    System.out.println();
    return readedChoice;
  }

  private static void aboutApp() throws IOException {
    System.out.println("""
                        >> TRAVEL COST <<
             This program was created by a man who loves to travel.
             Designed to quickly calculate your trip and optimize your expenses.
        """);
    runMenu();
  }

  private static void exit() {
    System.out.println("""
           We wish you a good trip! 
                  Goodbye!
        """);
  }


}
