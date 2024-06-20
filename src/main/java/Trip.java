import java.util.List;
import java.util.Scanner;

public class Trip implements TripInt {
  private TripDTO trip = new TripDTO();
  private Integer days;
  private Integer people;
  private static FileManager fileManager = FileManager.getInstance();
  private static Runner runner = Runner.getInstance();
  private static TripManager tripManager = TripManager.getInstance();

  @Override
  public TripDTO create() {
    List<TripDTO> trips = fileManager.readFile();

    if (trips.size() >= 3){
      System.out.println("You can add maximum 3 trips.");
      return null;
    } else {

      trip = new TripDTO(readName(trips),
          readDays(),
          readPeople(),
          readCurrency(),
          Calculator.appartCalc(people, days),
          Calculator.transferCalc(people),
          Calculator.localTransportCalc(),
          Calculator.foodCalc(days),
          Calculator.excursionCalc(),
          Calculator.entertainmentCalk(),
          0.00, 0.00
      );

      trip.setTotalOne(Calculator.totalOneCalc(
          trip.getAccommodation(), trip.getTransfer(), trip.getLocalTransport(), trip.getFood(),
          trip.getExcursions(), trip.getEntertainment()
      ));

      trip.setTotalAll(Calculator.totalAllCalc(trip.getTotalOne(), trip.getPeople()));

      trips.add(trip);
      fileManager.writeFile(trips);

      return trip;
    }
  }

  @Override
  public TripDTO edit(TripDTO trip) {
    trip.toString();
    System.out.println("Enter a number [1...6] of item you want to edit, or enter any to cancel: ");
    int choice = -1;
    do {
        choice = tripManager.readChoice();
      }while (choice >= 0 && choice <=7);

    switch (choice){
      case 1: trip.setAccommodation(Calculator.appartCalc(trip.getPeople(), trip.getDays()));
      case 2: trip.setTransfer(Calculator.transferCalc(trip.getPeople()));
      case 3: trip.setLocalTransport(Calculator.localTransportCalc());
      case 4: trip.setFood(Calculator.foodCalc(trip.getDays()));
      case 5: trip.setExcursions(Calculator.excursionCalc());
      case 6: trip.setEntertainment(Calculator.entertainmentCalk());
      case 0: runner.run();
    }
    trip.setTotalOne(Calculator.totalOneCalc(
        trip.getAccommodation(), trip.getTransfer(), trip.getLocalTransport(), trip.getFood(),
        trip.getExcursions(), trip.getEntertainment()
    ));

    trip.setTotalAll(Calculator.totalAllCalc(trip.getTotalOne(), trip.getPeople()));
    return trip;
  }

  @Override
  public List<TripDTO> delete(List<TripDTO> trips) {
    System.out.println();
    System.out.printf("Enter a number of trip you want to delete: 1 - %s\n", trips.size());
    System.out.println("If you want to cancel press any button");

    int i = tripManager.readChoice();

    if(i >= 1 && i <= trips.size()){
      trips.get(i-1).toString();

      System.out.println();
      System.out.println("Do you want to delete this trip?");

      if (Calculator.readYesNo()){
        trips.remove(i - 1);
      }
    }
    return trips;
  }


  private String readName(List<TripDTO> trips) {
    Scanner scanner = new Scanner(System.in);
    String tripName = null;

    System.out.println("Enter the unique name of the trip: ");

    while (true) {
      tripName = scanner.nextLine().trim();

      if (tripName.isEmpty()) {
        System.out.println("Trip name cannot be empty. Please enter a valid name.");
        continue;
      }

      if (isTripNameExists(trips, tripName)) {
        System.out.println("Trip with this name already exists. Please enter a unique name.");
      } else {
        break;
      }
    }

    return tripName;
  }

  private static boolean isTripNameExists(List<TripDTO> trips, String tripName) {
    for (TripDTO trip : trips) {
      if (trip.getName().equals(tripName)) {
        return true;
      }
    }
    return false;
  }

  private int readDays() {
    System.out.println("How many days do you plan to rest: ");
    do {
      try{
        days = Integer.parseInt(TripManager.readLine());
      } catch (NumberFormatException e){
        e.printStackTrace();
        runner.run();
      }
    } while (days <= 0);
    return days;
  }

  private int readPeople() {
    System.out.println("How many people will there be: ");
    do {
      try{
        people = Integer.parseInt(TripManager.readLine());
      } catch (NumberFormatException e){
        System.out.println("[Invalid input]: " + e.getMessage());
        readPeople();
      }
    } while (people <= 0);
    return people;
  }

  private String readCurrency() {
    System.out.print("Enter the currency in which the payment will be made: ");
    return TripManager.readLine().toUpperCase();
  }

}