package md.tekwill.exceptions;

public class ProductExistException extends RuntimeException {

    public ProductExistException(String message) {
        super(message);
    }
}
