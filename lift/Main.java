public class Main {

    public static void main(String[] args) throws InterruptedException {
        Controller controller = new Controller(2, 5, 0);
        controller.start();
        controller.call(1, 2);
        controller.call(2, 1);
        controller.call(1, 4);
        controller.call(2, 2);
        controller.call(1, 1);
        controller.call(2, 5);
        controller.call(2, 3);
        controller.call(1, 5);
        controller.call(2, 0);
    }
}
