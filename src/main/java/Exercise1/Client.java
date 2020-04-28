package Exercise1;

public class Client {
    public static void main(String[] args) throws InterruptedException{
        Alarm fireAlarm = new Alarm();
        AlarmMonitor monitor = new AlarmMonitor();
        AlarmController controller = new AlarmController();
        fireAlarm.register(monitor);
        fireAlarm.register(controller);

        fireAlarm.startAlarm();
        Thread.sleep(500);
        fireAlarm.stopAlarm();
    }

}
