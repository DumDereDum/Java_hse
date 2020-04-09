import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Elevator extends Thread {

    private int id;
    private int maxFloor;
    private int minFloor;
    private int currFloor;
    private boolean direction;
    private ArrayList<Integer> queue = new ArrayList<>();
    private ArrayList<Passenger> inside = new ArrayList<>();
    private ArrayList<Passenger> waitingPassengers = new ArrayList<>();

    public Elevator(int id, int maxFloor, int minFloor) {
        this.id = id;
        this.maxFloor = maxFloor;
        this.minFloor = minFloor;
        this.currFloor = minFloor;
        this.queue.add(maxFloor);
    }

    public void call(Passenger passenger) {
        if (this.getCurrFloor() == passenger.getCurrFloor() && this.direction == passenger.getDirection()) {
            this.enter(passenger);
        } else {
            waitingPassengers.add(passenger);
        }
    }

    private int getCurrFloor() {
        return this.currFloor;
    }

    private void enter(Passenger passenger) {
        this.queue.add(passenger.getFloor());
        this.inside.add(passenger);
    }

    private void checkInside() {
        if (this.inside.size() > 0) {
            for(int i = 0; i < this.inside.size(); i++) {
                if (this.currFloor == this.inside.get(i).getFloor()) {
                    this.inside.remove(i);
                    this.queue.remove((Integer) this.currFloor);
                    break;
                }
            }
        }
    }

    private void checkWaitingPassengers() {
        if (this.waitingPassengers.size() > 0) {
            for (int i = 0; i < this.waitingPassengers.size(); i++) {
                if (this.direction == this.waitingPassengers.get(i).getDirection() && this.currFloor == this.waitingPassengers.get(i).getCurrFloor()) {
                    this.enter(waitingPassengers.get(i));
                    this.waitingPassengers.remove(i);
                    this.queue.remove((Integer) this.currFloor);
                    break;
                }
            }
            Collections.sort(queue);
        }
    }

    private void arrivingPrint() {
        try {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Elevator " + this.id + " arrived to " + this.currFloor + " floor" +
                "\n\tinside: " + this.inside.size() + " passengers" +
                "\n\twait: " + this.waitingPassengers.size() + " passengers");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkInf() {
        System.out.println("Floor: " + this.currFloor);
        System.out.println("\tq:  " + this.queue);
        System.out.println("\tin: " + this.inside.size());
        System.out.println("\tw:  " + this.waitingPassengers.size());
    }

    private void moving() {
        if (!this.queue.isEmpty()) {
                if (this.currFloor < this.queue.get(0)) {
                    this.direction = true;
                    for (int i = this.currFloor; i < this.queue.get(this.queue.size() - 1); i++) {
                        this.currFloor++;
                        this.checkInside();
                        this.checkWaitingPassengers();
                        this.arrivingPrint();
                        if (this.queue.size() == 0) { break;}
                    }
                } else if (this.currFloor > this.queue.get(0)) {
                    this.direction = false;
                    for (int i = this.currFloor; i > this.queue.get(0); i++) {
                        this.currFloor--;
                        this.checkInside();
                        this.checkWaitingPassengers();
                        this.arrivingPrint();
                        if (this.queue.size() == 0) { break;}
                    }
                } else {
                    this.queue.remove(0);
                }
        } else if (!this.waitingPassengers.isEmpty()){
            Passenger tmp = this.waitingPassengers.get(0);
            int distance = Math.abs(this.currFloor - this.waitingPassengers.get(0).getCurrFloor());
            for (int i = 1; i < this.waitingPassengers.size(); i++) {
                int d = Math.abs(this.currFloor - this.waitingPassengers.get(i).getCurrFloor());
                if (d < distance) {
                    distance = d;
                    tmp = this.waitingPassengers.get(i);
                }
            }
            this.queue.add(tmp.getCurrFloor());
        }
    }

    @Override
    public void run() {
        while (true) {
            this.moving();
        }
    }

}
