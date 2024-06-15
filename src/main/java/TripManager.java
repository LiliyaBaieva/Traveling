import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TripManager {

  private FileManager fileManager = new FileManager();

  public void printTrips() {
    List<TripDTO> trips = fileManager.readFile();
    for(TripDTO trip : trips){
      trip.toString();
    }
  }


}

