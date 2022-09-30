package controller;

import ihm.GUI;

public class Grid {
    final Person [][] tab;
    int height;
    int width;
    GUI gui;


    public Grid(int height,int width){
        this.height = height;
        this.width = width;
        this.tab = new Person[height][width];
    }
    public void moveInGrid(Position start, Position arrival, Person person){
        tab[start.y][start.x]=null;
        tab[arrival.y][arrival.x+1]=person;
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
        
    }
}
