package Exercise2;

public class Client {

    public static void main(String args[]){
        Sensor sensor1 = new Sensor(SensorType.TEMPERATURE);
        Sensor sensor2 = new Sensor(SensorType.HUMIDITY);
        Sensor sensor3 = new Sensor(SensorType.PRESSURE);
        Controller controller = new Controller();
        sensor1.register(controller);
        sensor2.register(controller);
        sensor3.register(controller);
        sensor1.readSensor();
        sensor2.readSensor();
        sensor3.readSensor();
        sensor1.readSensor();
    }
}
