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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
