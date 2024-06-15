import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TripDTO {

  private String name;
  private Integer days;
  private Integer people;
  private String currency;
  private Double accommodation;
  private Double transfer;
  private Double localTransport;
  private Double food;
  private Double excursions;
  private Double entertainment;
  private Double totalOne;
  private Double totalAll;



  @Override
  public String toString() {

    String line = doFrame(name);

    return String.format(line +
        "||" + name + "||\n"+
        line +
        "[1]  Accommodation = %.2f (" + getCurrency() + ") \n"
        + "[2]  Transfer = %.2f (" + getCurrency() + ") \n"
        + "[3]  Travel by local transport = %.2f (" + getCurrency() + ") \n"
        + "[4]  Food = %.2f (" + getCurrency() + ") \n"
        + "[5]  Excursions = %.2f (" + getCurrency() + ") \n"
        + "[6]  Entertainment = %.2f (" + getCurrency() + ") \n"
        + "=============================================================\n"
        + "The amount of your trip will be: per 1 person = %.2f (" + getCurrency() + ")\n"
        + "                               per company   = %.2f (" + getCurrency() + ")\n"
        + "=============================================================\n\n",
        getAccommodation(), getTransfer(), getLocalTransport(), getFood(),
        getExcursions(), getEntertainment(), getTotalOne(), getTotalAll());

  }

  public String doFrame(String name) {
    String line = "======";
    for(int i = 0; i < name.length(); i++ ){
      line = line + "=";
    }
    line = line + "\n";
    return line;
  }
}
