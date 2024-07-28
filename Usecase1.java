import java.util.ArrayList;
import java.util.List;

interface Observer {
    void update(float temperature);
}

class WeatherStation {
    private List<Observer> observers = new ArrayList<>();
    private float temperature;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }
}

class PhoneDisplay implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("Phone display: Current temperature is " + temperature + "°C");
    }
}

class LCDPanel implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("LCD panel: Current temperature is " + temperature + "°C");
    }
}

public class Usecase1 {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        PhoneDisplay phoneDisplay = new PhoneDisplay();
        LCDPanel lcdPanel = new LCDPanel();

        weatherStation.addObserver(phoneDisplay);
        weatherStation.addObserver(lcdPanel);

        weatherStation.setTemperature(45);
        weatherStation.setTemperature(30);
    }
}