package controller;

public class Grid {
    final Person [][] tab;
    int height;
    int width;

    public Grid(int height,int width){
        this.height = height;
        this.width = width;
        this.tab = new Person[height][width];
    }
    public void mooveInGrid(Position start,Position arrival,Person person){
        tab[start.y][start.x]=null;
        tab[arrival.y][arrival.x+1]=person;

    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
