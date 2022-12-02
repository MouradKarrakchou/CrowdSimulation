package controller;

import ihm.GUI;


import java.util.concurrent.locks.ReentrantLock;

public class Grid {
//    final Person [][] tab;
//    final ReentrantLock[][] locks;
    final Box[][] boxes;
    int height;
    int width;
    GUI gui;


    public Grid(int height,int width){
        this.height = height;
        this.width = width;
//        this.tab = new Person[height][width];
//        this.locks = new ReentrantLock[height][width];
        boxes = new Box[height][width];

        // init the lock array
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                boxes[i][j] = new Box(new Position(j, i));
            }
        }
    }

    public boolean moveInGrid(Box start, Box arrival, Person person) throws InterruptedException {
        boolean hasMoved = arrival.move(start);

//        tab[start.y][start.x]=null;
//        tab[arrival.y][arrival.x]=person;
        if (Controller.DISPLAY && hasMoved)
            gui.updatePersonPosition(person, start.position, arrival.position);

        return hasMoved;
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

//    public Person[][] getTab() {
//        return tab;
//    }

    public void deletePerson(Position position) {
        boxes[position.y][position.x].left();
//        tab[position.y][position.x]=null;
        if (Controller.DISPLAY)
            gui.deletePersonByPosition(position);
    }
    public void finishGame(Position position) {
        boxes[position.y][position.x].left();
//        System.out.println("FINISH");
        if (Controller.DISPLAY)
            gui.finishGame(position);
    }

    public void putPerson(Person person) throws InterruptedException {
        boolean haveMoved = boxes[person.position.y][person.position.x].spawn(person);
//        locks[person.position.y][person.position.x].lock();
//        Person neighboor=tab[person.position.y][person.position.x];
        if (!haveMoved)
        {
            person.destroy();
//            Thread.sleep(1);
        }
        else
        {
//            tab[person.position.y][person.position.x]=person;
            if (Controller.DISPLAY)
                gui.putPerson(person);
        }
//        locks[person.position.y][person.position.x].unlock();
    }

    public Box[][] getBoxes() {
        return boxes;
    }
}
