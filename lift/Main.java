public class Main {

    public static void main(String[] args) throws InterruptedException {
        Controller controller = new Controller(2, 5, 0);
        controller.start();

        Passenger p1 = new Passenger(1, 5);
        Passenger p2 = new Passenger(3, 4);
        Passenger p3 = new Passenger(4, 2);
        Passenger p4 = new Passenger(3, 0);

        Passenger P1 = new Passenger(0, 4);
        Passenger P2 = new Passenger(2, 3);
        Passenger P3 = new Passenger(5, 3);
        Passenger P4 = new Passenger(4, 1);

        controller.call(1,p1);
        controller.call(1,p2);
        controller.call(1,p3);
        controller.call(1,p4);

        controller.call(2,P1);
        controller.call(2,P2);
        controller.call(2,P3);
        controller.call(2,P4);

    }
}
