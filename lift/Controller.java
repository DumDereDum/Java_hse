import java.util.HashMap;
import java.util.Map;

public class Controller{
    private int numOfElevators;
    private HashMap<Integer, Elevator> elevators = new HashMap<>();

    public Controller(int numOfElevators, int maxFlore, int minFlore) {
        for (int i = 0; i < numOfElevators; i++) {
            this.elevators.put(i+1, new Elevator(i+1, maxFlore, minFlore));
        }
    }

    public void call(int id, int flore) {
        if (this.elevators.get(id) != null) {
            this.elevators.get(id).call(flore);
        } else {
            System.out.println("Error, the id is invalid");
        }
    }

    public void move(int id, int flore) {
        if (this.elevators.get(id) != null) {
            this.elevators.get(id).move(flore);
        } else {
            System.out.println("Error, the id is invalid");
        }
    }

    public void start() {
        for (Map.Entry<Integer, Elevator> pair: this.elevators.entrySet())
        {
            pair.getValue().start();
            System.out.println("Elevator " + pair.getKey() + " ON");
        }
    }
}
