public class Passenger {

    private int currFloor;
    private int floor;
    private boolean direction;

    public Passenger(int currFloor, int floor) {
        this.currFloor = currFloor;
        this.floor = floor;
        this.direction = this.findDirection(currFloor, floor);
    }

    private boolean findDirection(int f1, int f2) {
        return f1 <= f2;
    }

    public int getCurrFloor() {
        return this.currFloor;
    }

    public int getFloor() {
        return this.floor;
    }

    public boolean getDirection() {
        return this.direction;
    }

}
