package controller;

public class Grid {
    private final int [][] tab;
    private int height;
    private int width;

    public Grid(int height,int width){
        this.height = height;
        this.width = width;
        this.tab = new int[height][width];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
