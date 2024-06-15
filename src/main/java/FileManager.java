import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

  public void writeFile(List<TripDTO> trips){
    File tripsFile = new File("res/trips.csv");

    if (!tripsFile.exists()) {
      try{
        tripsFile.createNewFile();
      } catch (IOException e){
        e.printStackTrace();
      }
    }

    try (CSVWriter writer = new CSVWriter(new FileWriter("resources/Trips.csv"))) {
      StatefulBeanToCsv<TripDTO> tripsToCsv = new StatefulBeanToCsvBuilder<TripDTO>(writer)
          .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
          .build();

      tripsToCsv.write(trips);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public List<TripDTO> readFile(){
    File tripsFile = new File("res/Trips.csv");
    if (!tripsFile.exists()) {
      System.out.println("File not found. Or you haven’t created more than one trip yet.");
    }


    StringReader reader = new StringReader("res/Trips.csv");
    CsvToBean<TripDTO> csvToTrips = new CsvToBeanBuilder<TripDTO>(reader)
        .withType(TripDTO.class)
        .withIgnoreLeadingWhiteSpace(true)
        .build();

    return csvToTrips.parse();
  }

}
