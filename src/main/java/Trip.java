import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Trip implements TripInterface{
  private TripDTO trip = new TripDTO();
  private Integer days;
  private Integer people;

  @Override
  public TripDTO create() {

    trip = new TripDTO(readName(),
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

  private String readName() {
    System.out.println("Enter the name of the trip: ");
    return readLine();
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
