package controller;

import java.awt.*;
import java.util.Random;

public class Person{
    final Person [][] tab;
    Position position;
    Position goal;
    Color color;
    public Person(Position position,Position goal,Person[][] tab ){
        this.position=position;
        this.goal=goal;
        this.tab=tab;
        Random random = new Random();
        this.color = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
    }

    public void makeChoice() {
        if (!makeMooveLine()) makeMoveColon();

    }
    public boolean makeMooveLine() {
        if (position.x< goal.x){
            tab[position.y][position.x]=null;
            tab[position.y][position.x+1]=this;
            position.x++;
            return true;
        }
        else if (position.x> goal.x){
            tab[position.y][position.x]=null;
            tab[position.y][position.x-1]=this;
            position.x--;
            return true;
        }
        else return false;
    }
    public boolean makeMoveColon() {
        return false;
    }

    public Color getColor() {
        return color;
    }
}