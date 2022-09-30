package controller;

import java.util.ArrayList;

public class Controller {
    Grid grid;
    ArrayList<Person> allPerson;

    public Controller(int height, int width){
        grid=new Grid(height,width);
        allPerson.add(new Person(new Position(0,0),new Position(2,0),grid));
        allPerson.add(new Person(new Position(0,0),new Position(0,2),grid));
        allPerson.add(new Person(new Position(0,0),new Position(0,2),grid));
    }
    public void startRound() throws InterruptedException {
        while (allPerson.size()>0) {
            allPerson.removeIf(person -> !person.makeChoice());
            Thread.sleep(2000);
        }
    }
}
