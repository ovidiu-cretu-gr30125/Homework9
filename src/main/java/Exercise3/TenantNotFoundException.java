package Exercise3;

public class TenantNotFoundException extends  Exception {
    public String name;

    public TenantNotFoundException(String name,String msg){
        super(msg);
        this.name=name;
    }
}
