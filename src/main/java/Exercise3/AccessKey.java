package Exercise3;

public class AccessKey {
    private String pin;

    public AccessKey(String pin){
        this.pin=pin;
    }

    public String getPin() {
        return pin;
    }

    @Override
    public String toString() {
        return "AccessKey{" +
                "pin='" + pin + '\'' +
                '}';
    }
}
