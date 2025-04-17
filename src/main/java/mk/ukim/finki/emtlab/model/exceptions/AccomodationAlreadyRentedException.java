package mk.ukim.finki.emtlab.model.exceptions;

public class AccomodationAlreadyRentedException extends RuntimeException{
    public AccomodationAlreadyRentedException() {
        super("Accommodation is already rented exception");
    }
}
