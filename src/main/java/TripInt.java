import java.util.List;

public interface TripInt {
  TripDTO create();
  TripDTO edit(TripDTO trip);
  List<TripDTO> delete(List<TripDTO> trips);

}
