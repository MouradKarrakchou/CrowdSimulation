import controller.Controller;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Controller controller=new Controller(10,15);
        controller.startRound();
    }
}
