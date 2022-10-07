package input;

import controller.Person;
import controller.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PersonGenerator {
    int height, width,numberOfPerson;
    Random r=new Random();
    
    public PersonGenerator(int height, int width,int numberOfPerson) {
        this.height = height;
        this.width = width;
        this.numberOfPerson=numberOfPerson;
    }

    public void createArrayPositionDepart(){
        ArrayList<Position> departure=new ArrayList<>();
        ArrayList<Position> arrival=new ArrayList<>();
        ArrayList<Color> colors=new ArrayList<>();
        for(int k=0;k<numberOfPerson;k++){
            departure.add(new Position(r.nextInt(width),r.nextInt(height)));
            switch (r.nextInt(3)){
                case 0:
                    arrival.add(new Position(0,r.nextInt(height)));
                case 1:
                    arrival.add(new Position(width,r.nextInt(height)));
                case 2:
                    arrival.add(new Position(r.nextInt(width),0));
                case 3:
                    arrival.add(new Position(r.nextInt(width),width));
            }
            colors.add(new Color(r.nextFloat(), r.nextFloat(), r.nextFloat()));
        }

    }

}
