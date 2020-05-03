package Exercise3;

public class SafeHome {

    public static void main(String[] args) throws Exception{
        Door door = new Door(DoorStatus.CLOSE);
        DoorLockController dr1 = new DoorLockController(door);
        ControllerInterface ctrl1=dr1;
        ///ctrl1.addTenant("1234","User1");
        GUI gui = new GUI();
        dr1.getLogs();
    }
}
