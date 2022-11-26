package controller;

import ihm.GUI;


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
        if (Controller.DISPLAY)
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
        if (Controller.DISPLAY)
            gui.deletePersonByPosition(position);
    }
    public void finishGame(Position position) {
        tab[position.y][position.x]=null;
//        System.out.println("FINISH");
        if (Controller.DISPLAY)
            gui.finishGame(position);
    }

    public void putPerson(Person person) throws InterruptedException {
        locks[person.position.y][person.position.x].lock();
        Person neighboor=tab[person.position.y][person.position.x];
        if (neighboor!=null)
        {
            person.destroy();
            Thread.sleep(1);
        }
        else
        {
            tab[person.position.y][person.position.x]=person;
            if (Controller.DISPLAY)
                gui.putPerson(person);
        }
        locks[person.position.y][person.position.x].unlock();
    }
}
