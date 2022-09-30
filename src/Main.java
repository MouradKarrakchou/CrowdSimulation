import controller.Controller;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Controller controller=new Controller(10,15);
        controller.startRound();
    }
}
