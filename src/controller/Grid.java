package controller;

import ihm.GUI;


import java.util.concurrent.locks.ReentrantLock;

public class Grid {
    final Box[][] boxes;
    int height;
    int width;
    GUI gui;


    public Grid(int height,int width){
        this.height = height;
        this.width = width;
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

    public void deletePerson(Position position) {
        boxes[position.y][position.x].left();
        if (Controller.DISPLAY)
            gui.deletePersonByPosition(position);
    }
    public void finishGame(Position position) {
        boxes[position.y][position.x].left();
        if (Controller.DISPLAY)
            gui.finishGame(position);
    }
    public void initPerson(Person person) throws InterruptedException {
        boolean hasMoved = boxes[person.position.y][person.position.x].init(person);
        if(hasMoved && Controller.DISPLAY)
                gui.putPerson(person);
    }

    public void putPerson(Person person) throws InterruptedException {
        boolean hasMoved = boxes[person.position.y][person.position.x].spawn(person);
        if (Controller.DISPLAY&&hasMoved)
            gui.putPerson(person);
    }

    public Box[][] getBoxes() {
        return boxes;
    }
}
