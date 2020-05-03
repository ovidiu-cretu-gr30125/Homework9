package Exercise3;

public class SafeHome {

    public static void main(String[] args) throws Exception{
        Door door = new Door(DoorStatus.CLOSE);
        DoorLockController dr1 = new DoorLockController(door);
        ControllerInterface ctrl1;
       /// dr1.addTenant("123","User1");
        ///dr1.addTenant("124","b");
       /// dr1.addTenant("125","a");
        ///dr1.removeTenant("User1");
        ///dr1.enterPin("124");
        GUI gui = new GUI();
        dr1.getLogs();
    }
}
