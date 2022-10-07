package controller;

import java.awt.*;
import java.util.Random;

public class Person{
    final Person [][] tab;
    Position position;
    Position goal;
    Color color;
    Grid grid;
    public Person(Position position,Position goal,Grid grid ){
        this.position=position;
        this.goal=goal;
        this.tab=grid.tab;
        this.grid=grid;
        Random random = new Random();
        this.color = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
    }

    /**
     *
     * @return true if he has made a choice, return false if he reached his goal
     */
    public boolean makeChoice() {
        if (!makeMooveLine()) if(!makeMoveColon()) {
            tab[position.y][position.x]=null;
            grid.deletePerson(position);
            return false;
        }
        return true;
    }
    public boolean makeMooveLine() {
        if (position.x< goal.x){
            grid.moveInGrid(position,new Position(position.x+1, position.y),this);
            position.x++;
            return true;
        }
        else if (position.x> goal.x){
            grid.moveInGrid(position,new Position(position.x-1, position.y),this);
            position.x--;
            return true;
        }
        else return false;
    }
    public boolean makeMoveColon() {
        if (position.y< goal.y){
            grid.moveInGrid(position,new Position(position.x, position.y+1),this);
            position.y++;
            return true;
        }
        else if (position.y> goal.y){
            grid.moveInGrid(position,new Position(position.x, position.y-1),this);
            position.y--;
            return true;
        }
        else return false;
    }

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public Position getGoal() {
        return goal;
    }
}