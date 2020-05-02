package Exercise3;

public class InvalidPinException extends Exception {
    public String pin;

    public InvalidPinException(String pin,String msg){
        super(msg);
        this.pin=pin;
    }
}
