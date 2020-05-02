package Exercise3;

import javax.imageio.IIOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoorLockController implements ControllerInterface{
    private List<AccessLog> accessLogList = new ArrayList<AccessLog>();

    private List<Tenant> tenantList = new ArrayList<Tenant>();

    private List<AccessKey> accessKeyList = new ArrayList<AccessKey>() ;

    private Map<Tenant,AccessKey> validAccess;

    private Door door;

    public int attemptsNumber;

    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public DoorLockController(Door door){
        this.door=door;
        this.validAccess = new HashMap<>();
        this.attemptsNumber=0;
    }
    @Override
    public DoorStatus enterPin(String pin) throws InvalidPinException, TooManyAttemptsException, IOException {
        if(attemptsNumber<2) {
            if (checkPin(pin, validAccess)) {
                if (door.getStatus().equals(DoorStatus.OPEN)) {
                    door.lockDoor();
                } else {
                    door.unlockDoor();
                }
                accessLogList.add(new AccessLog(getNameByPin(pin),LocalDateTime.now(),"EnterPin",door.getStatus(),"No error"));
                saveToFile(new AccessLog(getNameByPin(pin),LocalDateTime.now(),"EnterPin",door.getStatus(),"No error"));
            } else {
                door.lockDoor();
                attemptsNumber++;
                accessLogList.add(new AccessLog(getNameByPin(pin),LocalDateTime.now(),"EnterPin",door.getStatus(),"Invalid pin!"));
                saveToFile(new AccessLog(getNameByPin(pin),LocalDateTime.now(),"EnterPin",door.getStatus(),"Invalid pin!"));
                throw new InvalidPinException(pin, "Invalid pin!");
            }
        }
        else {
            if (attemptsNumber == 2) {
                if (!pin.equals(ControllerInterface.MASTER_KEY)) {
                    door.unlockDoor();
                    accessLogList.add(new AccessLog(getNameByPin(pin),LocalDateTime.now(),"EnterPin",door.getStatus(),"Too many attempts! Enter master key to unlock the door"));
                    saveToFile(new AccessLog(getNameByPin(pin),LocalDateTime.now(),"EnterPin",door.getStatus(),"Too many attempts! Enter master key to unlock the door"));
                    throw new TooManyAttemptsException(pin, "Too many attempts! Enter master key to unlock the door");
                }
                else{
                    door.unlockDoor();
                    attemptsNumber=0;
                    accessLogList.add(new AccessLog(getNameByPin(pin),LocalDateTime.now(),"EnterPin",door.getStatus(),"Master key used"));
                    saveToFile(new AccessLog(getNameByPin(pin),LocalDateTime.now(),"EnterPin",door.getStatus(),"Master key used"));
                }
            }
        }
        return door.getStatus();
    }
    /**
     * this method should return the name by pin from hash table
     * @param pin the pin for the name
     * @return the name from the hash table that requires to the given pin
     */
    public String getNameByPin(String pin){
        for (Map.Entry<Tenant, AccessKey> map : validAccess.entrySet()){
            if(pin.equals(map.getValue().getPin()))
                return map.getKey().getName();
        }
        return null;
    }
    /**
     * this method should verify the pin
     * @param pin the pin that is introduce by the user
     * @param validAccess the map with the pins and tenants names
     * @return true if the pin introduced by user matches with the pin from the map or false if not
     */
    public boolean checkPin(String pin,Map<Tenant,AccessKey> validAccess) {
        for (Map.Entry<Tenant, AccessKey> map : validAccess.entrySet()) {
            if (pin.equals(map.getValue().getPin()))
                return true;
        }
        return false;
    }
    /**
     * this method verify if exists a tenant in the @param tenantList by @param name
     * @param name the name of the tenant that is search
     * @param tenantList the list where we search
     * @return true if there is a tenant whit the @param name or false if there is not
     */
    public boolean existsTenant(String name,List<Tenant> tenantList){
        for(Tenant t: tenantList){
            if(t.getName().equals(name))
                return true;
        }
        return false;
    }
    @Override
    public void addTenant(String pin, String name) throws TenantAlreadyExistsException, IOException {
        if (existsTenant(name, tenantList)) {
            accessLogList.add(new AccessLog(name, LocalDateTime.now(), "addTenant", door.getStatus(), "Tenant already exists in the map"));
           saveToFile(new AccessLog(name, LocalDateTime.now(), "addTenant", door.getStatus(), "Tenant already exists in the map"));
            throw new TenantAlreadyExistsException(name, "Tenant already exists in the map");
        } else {
            tenantList.add(new Tenant(name));
            accessKeyList.add(new AccessKey(pin));
            validAccess.put(new Tenant(name), new AccessKey(pin));

            accessLogList.add(new AccessLog(name, LocalDateTime.now(), "AddTenant", door.getStatus(), "No error"));
           saveToFile(new AccessLog(name, LocalDateTime.now(), "AddTenant", door.getStatus(), "No error"));
        }
    }

    @Override
    public void removeTenant(String name) throws TenantNotFoundException, IOException {
        if(existsTenant(name,tenantList)){
            validAccess.entrySet().removeIf(t->t.getKey().getName().equals(name));
            tenantList.removeIf(t -> t.getName().equals(name));
            door.unlockDoor();
            accessLogList.add(new AccessLog(name,LocalDateTime.now(),"removeTenant",door.getStatus(),"no error"));
           saveToFile(new AccessLog(name,LocalDateTime.now(),"removeTenant",door.getStatus(),"no error"));
        }
        else{
            door.lockDoor();
            accessLogList.add(new AccessLog(name,LocalDateTime.now(),"removeTenant",door.getStatus(),"Tenant not found"));
            saveToFile(new AccessLog(name,LocalDateTime.now(),"removeTenant",door.getStatus(),"Tenant not found"));
            throw new TenantNotFoundException(name,"Tenant not found");
        }
    }

    /**
     * getter for the access log list
     * @return access log list
     */
    public List<AccessLog> getAccessLogs(){
        return accessLogList;
    }

    @Override
    public String toString() {
        return "DoorLockController{" +
                "accessLogList=" + accessLogList +
                '}';
    }

    /**
     *
     * this method save to a new file all the access logs one by one in a different file
     * @param accessLog the object that has to be saved in the file
     * @throws IOException throws exception if the file can not be created or the object is no serializable
     */
    public void saveToFile(AccessLog accessLog) throws IOException {
        try(
                final FileOutputStream fileOutputStream1 = new FileOutputStream("accessLog{"+ timestamp.getTime() +"}.dat");
                final ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);
        ){
            objectOutputStream1.writeObject(accessLog);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IIOException e){
            e.printStackTrace();
        }
    }

    /**
     * get logs from the files
     */
    public void getLogs(){
        AccessLogDisplay accessLogDisplay = new AccessLogDisplay();
        accessLogDisplay.readFile();
        accessLogDisplay.displayAccessLogList();
    }
}
