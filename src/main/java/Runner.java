import java.util.ArrayList;
import java.util.List;

public class Runner {

  private static Runner instance;

  private Runner(){}

  public static synchronized Runner getInstance() {
    if (instance == null) {
      instance = new Runner();
    }
    return instance;
  }

  private static TripManager tripManager = TripManager.getInstance();
  private static FileManager fileManager = FileManager.getInstance();


  public void run() {
    for (int i = 0; i < menu().size(); ++i) {
      System.out.println("[" + (i + 1) + "] " + menu().get(i));
    }

    int choice = readMenuChoice();
    switch (choice) {
      case 1 ->  tripManager.addTrip();
      case 2 -> tripManager.printAll();
      case 3 -> tripManager.edit();
      case 4 -> tripManager.deleteTrip();
      case 5 -> aboutApp();
      case 6 -> exit();
    }


  }

  private static List<String> menu() {
    List<String> menu = new ArrayList<>();
    menu.add("Create a trip"); //1
    menu.add("View all trip");  //2
    menu.add("Edit a trip");
    menu.add("Delete a trip");
    menu.add("About APP");
    menu.add("Quit"); //7
    return menu;
  }


  private static int readMenuChoice() {

    //  Считываем выбор меню от пользователя
    System.out.println("-----------------------------");
    System.out.print("    Make your choice: ");
    int choice = 0;
    try {
      choice = Integer.parseInt(TripManager.readLine());
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid input: \"" + e + "\"");
      readMenuChoice();
    }
    if (choice > 6) {
      System.out.println("Enter from 1 to 6");
      readMenuChoice();
    }
    System.out.println();
    return choice;
  }

  private void aboutApp() {
    System.out.println("""
                        >> TRAVEL COST <<
             This program was created by a man who loves to travel.
             Designed to quickly calculate your trip and optimize your expenses.
        """);
    run();
  }

  private static void exit() {
    fileManager.deleteFile();
    System.out.println("""
           We wish you a good trip! 
                  Goodbye!
        """);
  }


}
