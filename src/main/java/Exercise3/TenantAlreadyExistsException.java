package Exercise3;

public class TenantAlreadyExistsException extends  Exception {
    String name;

    public  TenantAlreadyExistsException(String name,String msg){
        super(msg);
        this.name=name;
    }
}
