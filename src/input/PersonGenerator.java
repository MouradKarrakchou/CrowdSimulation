package input;

import controller.Controller;
import controller.Position;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class PersonGenerator {
    int height, width,numberOfPerson;
    Random r=new Random();
    CSVManager csvManager=new CSVManager();
    
    public PersonGenerator() {
        this.height = Controller.HEIGHT;
        this.width = Controller.WIDTH;
        this.numberOfPerson=Controller.NUMBER_OF_PERSON;
    }

    public void createArrayPositionDepart() throws IOException {
        ArrayList<Position> departure=new ArrayList<>();
        ArrayList<Position> arrival=new ArrayList<>();
        ArrayList<Color> colors=new ArrayList<>();
        for(int k=0;k<numberOfPerson;k++){
            departure.add(new Position(r.nextInt(width),r.nextInt(height)));
            switch (r.nextInt(3)){
                case 0:
                    arrival.add(new Position(0,r.nextInt(height)));
                case 1:
                    arrival.add(new Position(width-1,r.nextInt(height)));
                case 2:
                    arrival.add(new Position(r.nextInt(width),0));
                case 3:
                    arrival.add(new Position(r.nextInt(width),height-1));
            }
            colors.add(new Color(r.nextFloat(), r.nextFloat(), r.nextFloat()));
        }
        csvManager.createPersonsCSV(departure,arrival,colors);

    }

    public static void main(String[] args) throws IOException {
        PersonGenerator personGenerator=new PersonGenerator();
        personGenerator.createArrayPositionDepart();
    }

}
