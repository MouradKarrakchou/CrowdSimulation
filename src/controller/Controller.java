package controller;

import ihm.GUI;

import java.util.ArrayList;

public class Controller {
    Grid grid;
    ArrayList<Person> allPerson;
    GUI gui;

    public Controller(int height, int width){
        grid=new Grid(height,width);
        this.allPerson=new ArrayList<>();
        allPerson.add(new Person(new Position(0,0),new Position(5,0),grid));
        allPerson.add(new Person(new Position(4,0),new Position(0,5),grid));
        allPerson.add(new Person(new Position(6,0),new Position(6,6),grid));
        gui=new GUI(grid);
        grid.setGui(gui);
    }
    public void startRound() throws InterruptedException {
        while (allPerson.size()>0) {
            allPerson.removeIf(person -> !person.makeChoice());
            Thread.sleep(1000);
        }
    }
}
