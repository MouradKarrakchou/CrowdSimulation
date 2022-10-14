package controller;

import ihm.GUI;
import input.CSVManager;
import input.PersonGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    Grid grid;
    List<Person> allPerson;
    GUI gui;
    public static final int HEIGHT = 120;
    public static final int WIDTH = 100;
    public static final int NUMBER_OF_PERSON = 100;
    public static final int TIME_TO_SLEEP = 1;
    public static final boolean GENERATE_PERSON = true;

    public Controller() throws IOException {
        if (GENERATE_PERSON)
            new PersonGenerator().createArrayPositionDepart();

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

    public void close() {
        gui.close();
    }
}
