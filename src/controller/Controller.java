package controller;

import java.util.ArrayList;

public class Controller {
    Grid grid;
    ArrayList<Person> allPerson;
    public Controller(int height, int width){
        grid=new Grid(height,width);
        allPerson.add(new Person(new Position(0,0),new Position(2,0),grid.tab));
    }
    public void startRound(){
        for (Person person : allPerson){
            person.makeChoice();
        }
    }
}
