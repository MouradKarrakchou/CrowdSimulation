package controller;

import ihm.GUI;
import input.CSVManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    Grid grid;
    ArrayList<Person> allPerson;
    GUI gui;
    public static final int HEIGHT =100;
    public static final int WIDTH =100;
    public static final int NUMBER_OF_PERSON =1000;

    public Controller(int height, int width) throws IOException {
        grid=new Grid(height,width);
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
}
