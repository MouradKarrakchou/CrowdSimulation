package controller;

import java.awt.*;
import java.util.Random;

public class Person{
    final Person [][] tab;
    Position startPosition;
    Position position;
    Position goal;
    Color color;
    Grid grid;
    //if comptReset<2 the player don't play
    int comptReset=4;
    int id;

    public Person(Position position,Position goal,Grid grid,int id ){
        this.id=id;
        this.startPosition=position;
        this.position=new Position(position.x,position.y);
        this.goal=goal;
        this.tab=grid.tab;
        this.grid=grid;
        Random random = new Random();
        this.color = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
    }

    public Person(Position position,Position goal,Grid grid,int id, Color color){
        this.id=id;
        this.startPosition=position;
        this.position=new Position(position.x,position.y);
        this.goal=goal;
        this.tab=grid.tab;
        this.grid=grid;
        Random random = new Random();
        this.color = color;
    }

    /**
     *
     * @return true if he has made a choice, return false if he reached his goal
     */
    public boolean makeChoice() throws InterruptedException {
        Thread.sleep(100);
        comptReset++;
        if (comptReset<4) {
            return true;
        }
        else if (comptReset==4){
            grid.putPerson(this);
        }
        if (!makeMooveLine()) if(!makeMoveColon()) {
            grid.finishGame(position);
            return false;
        }
        return true;
    }
    public boolean makeMooveLine() {
        Person neighboor;
        if (position.x< goal.x){
            neighboor=grid.tab[position.y][position.x+1];
            if (clearTheWay(neighboor)){
            grid.moveInGrid(position,new Position(position.x+1, position.y),this);
            position.x++;}
            return true;
        }
        else if (position.x> goal.x){
            neighboor=grid.tab[position.y][position.x-1];
            if (clearTheWay(neighboor)){
            grid.moveInGrid(position,new Position(position.x-1, position.y),this);
            position.x--;}
            return true;
        }
        else return false;
    }

    /**
     * remove himself from the grid and will restart at his initial position in 3 rounds
     */
    void destroy() {
        grid.deletePerson(position);
        position=new Position(startPosition.x,startPosition.y);
        comptReset=0;
        System.out.println("DESTROYED");
    }
    public boolean clearTheWay(Person neighboor){
        if (neighboor==null)
            return true;
        else if (neighboor.id<this.id)
        {
            neighboor.destroy();
            return true;
        }
        else
            this.destroy();
            return false;
    }
    public boolean makeMoveColon() {
        Person neighboor;
        if (position.y< goal.y){
            neighboor=grid.tab[position.y+1][position.x];
            if (clearTheWay(neighboor)){
            grid.moveInGrid(position,new Position(position.x, position.y+1),this);
            position.y++;}
            return true;
        }
        else if (position.y> goal.y){
            neighboor=grid.tab[position.y-1][position.x];
            if (clearTheWay(neighboor)){
            grid.moveInGrid(position,new Position(position.x, position.y-1),this);
            position.y--;}
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