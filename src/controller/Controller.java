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
        allPerson.add(new Person(new Position(0,0),new Position(5,0),grid,1));
        allPerson.add(new Person(new Position(4,0),new Position(0,5),grid,2));
        allPerson.add(new Person(new Position(6,0),new Position(6,6),grid,3));
        allPerson.add(new Person(new Position(5,6),new Position(5,3),grid,4));
        allPerson.add(new Person(new Position(7,9),new Position(2,5),grid,5));
        allPerson.add(new Person(new Position(6,4),new Position(6,3),grid,6));
        allPerson.add(new Person(new Position(7,2),new Position(1,0),grid,7));
        allPerson.add(new Person(new Position(9,9),new Position(0, 4),grid,8));
        allPerson.add(new Person(new Position(6,1),new Position(6,7),grid,9));
        gui=new GUI(grid);
        grid.setGui(gui);
        for (Person person:allPerson){
            grid.putPerson(person);
        }
    }
    public void startRound() throws InterruptedException {
        while (allPerson.size()>0) {
            ArrayList<Person> allPersonToRemove=new ArrayList<>();
            for (Person person:allPerson){
                if(!person.makeChoice()) {
                    allPersonToRemove.add(person);
                    System.out.println("Finished:"+person);
                }
            }
            allPerson.removeAll(allPersonToRemove);
        }
    }
}
