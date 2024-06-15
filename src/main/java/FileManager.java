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

  private static final String SEP = ";";

  public void writeFile(List<TripDTO> trips){
    File tripsFile = new File("res/trips.txt");

    if (!tripsFile.exists()) {
      try{
        tripsFile.createNewFile();
      } catch (IOException e){
        e.printStackTrace();
      }
    }

    try (CSVWriter writer = new CSVWriter(new FileWriter("resources/Trips.csv"))) {
      StatefulBeanToCsv<TripDTO> beanToCsv = new StatefulBeanToCsvBuilder<TripDTO>(writer)
          .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
          .build();

      beanToCsv.write(trips);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

//    File tripsFile = new File("res/trips.txt");
//    FileWriter fileWriter = new FileWriter("res/trips.txt");
//    if (!tripsFile.exists()) {
//      tripsFile.createNewFile();
//    }
//    fileWriter.close();

  }

  public List<TripDTO> readFile(){
    File tripsFile = new File("res/Trips.txt");
    if (!tripsFile.exists()) {
      System.out.println("File not found. Or you haven’t created more than one trip yet.");
      GoTravel.runMenu();
    }
    BufferedReader br = new BufferedReader(new FileReader("res/Trips.txt"));

    String line = br.readLine();
    int sep = line.indexOf(";");
//    return line.substring(0, sep);

    return new ArrayList<>();
  }

  private String parseTripToLine(TripDTO trips){

    return null;
  }

  private List<TripDTO> unParseTripFromLine(String file){
    StringReader reader = new StringReader(file);

    CsvToBean<TripDTO> csvToBean = new CsvToBeanBuilder<TripDTO>(reader)
        .withType(TripDTO.class)
        .withIgnoreLeadingWhiteSpace(true)
        .build();

    return csvToBean.parse();
  }

}
