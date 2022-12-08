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
    public static final int HEIGHT = 100;
    public static final int WIDTH = 100;
    public static final int NUMBER_OF_PERSON = 3000;
    public static final int TIME_TO_SLEEP = 10;
    public static final boolean GENERATE_PERSON = true;
    public static final boolean DISPLAY = true;
    public static final boolean VERBOSE = true;

    public Controller() throws IOException, InterruptedException {
        if (GENERATE_PERSON)
            new PersonGenerator().createArrayPositionDepart();

        grid=new Grid(HEIGHT,WIDTH);
        this.personInTransit = new CSVManager().getPersonList(grid);

        if (DISPLAY) {
            gui=new GUI(grid);
            grid.setGui(gui);
        }

        for (Person person: personInTransit){
            grid.initPerson(person);
        }
    }

    /**
     * start the simulation and finish when evrybody is out of the grid
     * @throws InterruptedException
     */
    public void execute() throws InterruptedException {
        long startTime = System.nanoTime(); // start timer

        for (Person person: personInTransit){
            person.start();
        }

        // wait for all persons to finish
        for (Person person: personInTransit){
            person.join();
        }

        long stopTime = System.nanoTime(); // end timer
        System.out.println("FINISH !");

        long timeInMs = (stopTime - startTime) / 1000000;
        System.out.println("DURATION : " + timeInMs + " ms");
    }

    public void close() {
        if (Controller.DISPLAY)
            gui.close();
    }
}
