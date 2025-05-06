package mk.ukim.finki.emtlab.model.exceptions;

public class AccommodationAlreadyRentedException extends RuntimeException{
    public AccommodationAlreadyRentedException() {
        super("Accommodation is already rented exception");
    }
}
