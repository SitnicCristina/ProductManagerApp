package md.tekwill.exceptions;

public class ProductUpdateUnknownPropertyException extends RuntimeException{

    public ProductUpdateUnknownPropertyException(String message){
        super(message);
    }
}
