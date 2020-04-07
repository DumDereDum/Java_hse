import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Elevator extends Thread {

    private int id;
    private int maxFlore;
    private int minFlore;
    private int currFlore;
    private ArrayList<Integer> queue = new ArrayList<>();

    public Elevator(int id,int maxFlore, int minFlore) {
        this.id = id;
        this.maxFlore = maxFlore;
        this.minFlore = minFlore;
        this.currFlore = minFlore;
        this.queue.add(maxFlore);
        this.queue.add(minFlore);
    }

    public void call(int flore) {
        if (this.minFlore <= flore && flore <= maxFlore && (queue.size() == 0 || flore != queue.get(queue.size()-1))) {
            this.queue.add(flore);
        } else {
            System.out.println("Error, the call is invalid");
        }
    }

    public void move(int flore) {
        if (this.minFlore <= flore && flore <= maxFlore && (queue.size() == 0 || flore != queue.get(queue.size()-1))) {
            this.queue.add(flore);
        } else {
            System.out.println("Error, the flore  is invalid");
        }
    }

    @Override
    public void run() {
        if (!this.queue.isEmpty()) {
            while (!this.queue.isEmpty()) {
                try {
                    TimeUnit.SECONDS.sleep(Math.abs(this.currFlore - this.queue.get(0)));
                    this.currFlore = this.queue.get(0);
                    this.queue.remove(0);
                    System.out.println("Elevator " + id + " arrived to " + this.currFlore + " flore");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
