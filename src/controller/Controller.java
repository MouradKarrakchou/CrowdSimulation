package controller;

import ihm.GUI;
import input.CSVManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    Grid grid;
    List<Person> allPerson;
    GUI gui;
    public static final int HEIGHT =100;
    public static final int WIDTH =100;
    public static final int NUMBER_OF_PERSON =1000;

    public Controller() throws IOException {
        grid=new Grid(HEIGHT,WIDTH);
        this.allPerson = new CSVManager().getPersonList(grid);
        gui=new GUI(grid);
        grid.setGui(gui);
        for (Person person:allPerson){
            grid.putPerson(person);
        }
    }
    public void startRound() throws InterruptedException {
        while (allPerson.size()>0) {
            System.out.println("Nb persons alive : " + allPerson.size());
            System.out.println(allPerson);
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
