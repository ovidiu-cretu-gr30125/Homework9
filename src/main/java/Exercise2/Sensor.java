package Exercise2;

import java.util.Random;

public class Sensor extends Observable{
    private SensorType sensorType;

    private int sensorValue;

    public Sensor(SensorType sensorType){
        this.sensorType=sensorType;
    }

    public void readSensor(){
        Random rand = new Random();
        sensorValue=rand.nextInt(100);
        changeState(this.toString());
    }

    public int getSensorValue(){
        return sensorValue;
    }
    public SensorType getSensorType() {
        return sensorType;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "sensorType=" + sensorType +
                ", sensorValue=" + sensorValue +
                '}';
    }
}
