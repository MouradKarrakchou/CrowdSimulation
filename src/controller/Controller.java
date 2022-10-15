package controller;

import ihm.GUI;
import input.CSVManager;
import input.PersonGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    Grid grid;
    // persons that didn't finish yet
    List<Person> personInTransit;
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
        this.personInTransit = new CSVManager().getPersonList(grid);
        gui=new GUI(grid);
        grid.setGui(gui);
        for (Person person: personInTransit){
            grid.putPerson(person);
        }
    }
    public void execute() throws InterruptedException {
        while (personInTransit.size()>0) {
            ArrayList<Person> allPersonToRemove=new ArrayList<>();
            for (Person person: personInTransit){
                if(!person.makeChoice()) {
                    allPersonToRemove.add(person);
                    System.out.println("Finished:"+person);
                }
            }
            personInTransit.removeAll(allPersonToRemove);
        }
    }

    public void close() {
        gui.close();
    }
}
