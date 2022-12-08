import controller.Controller;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
//        Controller controller=new Controller();
//        controller.execute();
//        controller.close();

        int[] sizes = {100};
        int[] numPerson = {10, 50, 100, 400, 700, 1000, 2000, 3000, 4000, 5000, 6000, 7000,8000,9000,10000};

        for (int i = 0; i < sizes.length; i++) {
            for (int j = 0; j < numPerson.length; j++) {
                long moyenne=0;
                for (int k = 0; k < 10 ; k++) {
                    moyenne +=new Controller(sizes[i], sizes[i], numPerson[j]).execute();
                }
                System.out.println(sizes[i] + "," + numPerson[j] + "," + moyenne/10);
            }
        }
    }
}
