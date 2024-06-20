import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
  private static FileManager instance;

  private FileManager() {}

  public static synchronized FileManager getInstance() {
    if (instance == null) {
      instance = new FileManager();
    }
    return instance;
  }

  public void writeFile(List<TripDTO> trips) {
    File tripsFile = new File("src/main/resources/Trips.csv");

    // Создание директории, если она не существует
    if (!tripsFile.getParentFile().exists()) {
      tripsFile.getParentFile().mkdirs();
    }

    if (!tripsFile.exists()) {
      try {
        tripsFile.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    try (CSVWriter writer = new CSVWriter(new FileWriter(tripsFile))) {
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

  public List<TripDTO> readFile() {
    File tripsFile = new File("src/main/resources/Trips.csv");

    if (!tripsFile.exists()) {
//      System.out.println("=== [ You haven’t created more than one trip yet. ] === \nOr file not found.\n");
      return new ArrayList<>();
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(tripsFile))) {
      CsvToBean<TripDTO> csvToTrips = new CsvToBeanBuilder<TripDTO>(reader)
          .withType(TripDTO.class)
          .withIgnoreLeadingWhiteSpace(true)
          .build();

      return csvToTrips.parse();
    } catch (IOException e) {
      e.printStackTrace();
      return new ArrayList<>();
    }
  }

  public boolean deleteFile() {
    File tripsFile = new File("src/main/resources/Trips.csv");

    if (!tripsFile.exists()) {
      System.out.println("File not found.");
      return false;
    }

    if (tripsFile.delete()) {
      System.out.println("File deleted successfully.");
      return true;
    } else {
      return false;
    }
  }

}
