import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class Trip implements TripInterface{
  private TripDTO trip = new TripDTO();
  private Integer days;
  private Integer people;
  private FileManager fileManager = new FileManager();
  private GoTravel goTravel;

  @Override
  public TripDTO create() {
    List<TripDTO> trips = fileManager.readFile();

    if (trips.size() >= 3){
      System.out.println("You can add maximum 3 trips.");
      goTravel.go();
    }

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

      return trip;
  }

  @Override
  public TripDTO edit() {
    return trip;
  }

  @Override
  public void delete() {

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
      days = Integer.parseInt(readLine());
    } while (days <= 0);
    return days;
  }

  private int readPeople() {
    System.out.println("How many people will there be: ");
    do {
      people = Integer.parseInt(readLine());
    } while (people <= 0);
    return people;
  }

  private String readCurrency() {
    System.out.print("Enter the currency in which the payment will be made: ");
    return readLine().toUpperCase();
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


}
