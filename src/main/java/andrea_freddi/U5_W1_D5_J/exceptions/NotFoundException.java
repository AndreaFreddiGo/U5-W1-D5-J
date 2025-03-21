package andrea_freddi.U5_W1_D5_J.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("L'elemento con id " + id + " non è stato trovato!");
    }
}
