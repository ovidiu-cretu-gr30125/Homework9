package Exercise2;

public class Controller implements Observer{

    @Override
    public void update(Object event) {
        System.out.println(event.toString());
    }
}
