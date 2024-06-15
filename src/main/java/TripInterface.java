import java.io.IOException;

public interface TripInterface {
  TripDTO create() throws IOException;
  TripDTO edit();
  void delete();

}
