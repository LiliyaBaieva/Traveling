import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class TripManager {

  private static TripManager instance;

  private TripManager(){}

  public static synchronized TripManager getInstance() {
    if (instance == null) {
      instance = new TripManager();
    }
    return instance;
  }

  private static Runner runner = Runner.getInstance();
  private static FileManager fileManager = FileManager.getInstance();

  private Trip tripOp = new Trip();

  public void addTrip() {
    tripOp.create();
    runner.run();
  }

  public void edit() {
    List<TripDTO> trips = printAllTripSum();

    if(trips.isEmpty()){
      System.out.println("=== [ You haven’t created more than one trip yet. ] ===\n");
      runner.run();
    }

    System.out.println();

    System.out.printf("Enter a number of trip 1-%s: \n", trips.size());
    System.out.println("To move to main menu press any button.");
    int choice = -1;
    while (choice < 0 && choice > trips.size()){
      choice = Integer.parseInt(readLine());
    }
    if(choice >= 1){
      tripOp.edit(trips.get(choice));
      fileManager.writeFile(trips);
    }

    runner.run();
  }

  public void printAll () {
    printAllTripInfo();
    System.out.println("******* TOTAL *******");
    printAllTripSum();
    runner.run();
  }

  public void deleteTrip() {
    List<TripDTO> trips = printAllTripSum();

    if(trips.isEmpty()){
      System.out.println("=== [ You haven’t created more than one trip yet. ] ===\n");
      runner.run();
    }

    tripOp.delete(trips);
    fileManager.writeFile(trips);
    runner.run();
  }

  public List<TripDTO> printAllTripInfo(){
    Table table = new Table();
    List<TripDTO> trips = fileManager.readFile();

    if(trips.isEmpty()){
      System.out.println("=== [ You haven’t created more than one trip yet. ] ===\n");
      runner.run();
    }

    // Set table headers
    try{
      table.setHeader(
          "Name of trip", "Appart", "Transfer", "Loc.tran", "Food", "Excurs.", "Entert."
      );
    } catch (MultipleHeaderException e) {
      e.printStackTrace();
    } catch (DuplicateHeaderException e) {
      e.printStackTrace();
    }

    // Add data/rows to the table
    try{
      for(int i = 0; i < trips.size(); i++){
        int n = (i + 1);
        table.addRow(
            "[" + n + "] " + trips.get(i).getName(),
            String.format("%.2f", trips.get(i).getAccommodation()),
            String.format("%.2f", trips.get(i).getTransfer()),
            String.format("%.2f", trips.get(i).getLocalTransport()),
            String.format("%.2f", trips.get(i).getFood()),
            String.format("%.2f", trips.get(i).getExcursions()),
            String.format("%.2f", trips.get(i).getEntertainment())
        );
      }
    } catch (ElementSizeException e) {
      throw new RuntimeException(e);
    }

    // Customize the table
    table.setHorizontalSeparator('-');
    table.setVerticalSeparator('|');
    table.setCornerJoint('+');
    table.setUppercaseHeaders(true);

    // Print the table
    try {
      table.printTable();
    } catch (ElementSizeException e) {
      throw new RuntimeException(e);
    }

    return trips;
  }

  public List<TripDTO> printAllTripSum(){
    Table table = new Table();
    List<TripDTO> trips = fileManager.readFile();

    // Set table headers
    try{
      table.setHeader("Name of trip", "Total for one", "Total for all");
    } catch (MultipleHeaderException e) {
      e.printStackTrace();
    } catch (DuplicateHeaderException e) {
      e.printStackTrace();
    }

    // Add data/rows to the table
    try{
      for(int i = 0; i < trips.size(); i++){
        int n = (i + 1);
        table.addRow(
            "[" + n + "] " + trips.get(i).getName(),
            String.format("%.2f", trips.get(i).getTotalOne()),
            String.format("%.2f", trips.get(i).getTotalAll())
        );
      }
    } catch (ElementSizeException e) {
      throw new RuntimeException(e);
    }

    // Customize the table
    table.setHorizontalSeparator('-');
    table.setVerticalSeparator('|');
    table.setCornerJoint('+');
    table.setUppercaseHeaders(true);

    // Print the table
    try {
      table.printTable();
    } catch (ElementSizeException e) {
      throw new RuntimeException(e);
    }
    return trips;
  }

  public static String readLine(){
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String result = null;
    try{
      result = br.readLine();
    } catch (IOException e){
      e.printStackTrace();
    }
    return result;
  }

  public int readChoice(){
    String choice = readLine();
    int i = -576;
    try{
      i = Integer.parseInt(choice);
    } catch (NumberFormatException e){
      runner.run();
    }
    return i;
  }
}