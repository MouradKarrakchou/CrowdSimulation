package controller;

import ihm.GUI;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Grid {
    final Person [][] tab;
    final ReentrantLock[][] locks;
    int height;
    int width;
    GUI gui;


    public Grid(int height,int width){
        this.height = height;
        this.width = width;
        this.tab = new Person[height][width];
        this.locks = new ReentrantLock[height][width];

        // init the lock array
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                locks[i][j] = new ReentrantLock();
            }
        }
    }

    public void moveInGrid(Position start, Position arrival, Person person){
        tab[start.y][start.x]=null;
        tab[arrival.y][arrival.x]=person;
        gui.updatePersonPosition(person,start,arrival);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public Person[][] getTab() {
        return tab;
    }

    public void deletePerson(Position position) {
        tab[position.y][position.x]=null;
        gui.deletePersonByPosition(position);
    }
    public void finishGame(Position position) {
        tab[position.y][position.x]=null;
        gui.finishGame(position);
    }

    public void putPerson(Person person) throws InterruptedException {
        Person neighboor=tab[person.position.y][person.position.x];
        if (neighboor!=null)
        {
            person.destroy();
        }
        else
        {
            tab[person.position.y][person.position.x]=person;
            gui.putPerson(person);
        }
    }
}
