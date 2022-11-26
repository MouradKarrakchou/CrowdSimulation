package controller;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Person implements Runnable {
    final Person [][] tab;
    Position startPosition;
    Position position;
    Position goal;
    Color color;
    Grid grid;
    // comptReset is used to check how much round since last destruction
    int comptReset=4;
    boolean reset = false;
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

    @Override
    public void run() {
        while (true) {
            try {
                if (!makeChoice()) break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * check comptReset to know if the Person is still destroyed or not after that make move the Person.
     * @return true if he has made a choice, return false if he reached his goal
     */
    public boolean makeChoice() throws InterruptedException {
        Thread.sleep(Controller.TIME_TO_SLEEP);

        /*
        comptReset++;
        if (comptReset<3) {
            return true;
        }
        else if (comptReset==3){
            grid.putPerson(this);
        }
         */

        if (reset) {
            grid.putPerson(this);
            reset = false;
        }


        if (comptReset!=0 && !makeMooveLine()) if(!makeMoveColon()) {
            grid.finishGame(position);
            return false;
        }
        return true;
    }


    public boolean makeMooveLine() throws InterruptedException {
        if (position.x == goal.x)
            return false;

        int move = 1;
        if (position.x > goal.x)
            move = -1;

        ReentrantLock lock = grid.locks[position.y][position.x + move];
        lock.lock();

        Person neighboor = grid.tab[position.y][position.x + move];
        if (clearTheWay(neighboor)){
            grid.moveInGrid(position,new Position(position.x + move, position.y),this);
            position.x += move;
        }

        lock.unlock();
        return true;
    }


    public boolean makeMoveColon() throws InterruptedException {
        if (position.y == goal.y)
            return false;

        int move = 1;
        if (position.y > goal.y)
            move = -1;

        ReentrantLock lock = grid.locks[position.y + move][position.x];
        lock.lock();

        Person neighboor = grid.tab[position.y + move][position.x];
        if (clearTheWay(neighboor)){
            grid.moveInGrid(position,new Position(position.x, position.y + move),this);
            position.y += move;
        }

        lock.unlock();
        return true;
    }

    /**
     * remove himself from the grid and will restart at his initial position in 3 rounds
     */
    void destroy() throws InterruptedException {
        grid.deletePerson(position);
        position=new Position(startPosition.x,startPosition.y);
        //comptReset=0;
        reset = true;
        System.out.println("DESTROYED " + id);
        Thread.sleep(10);
    }

    /**
     * decide who is going to be destroyed
     * @param neighboor
     * @return
     */
    public boolean clearTheWay(Person neighboor) throws InterruptedException {
        if (neighboor==null)
            return true;
        else if (neighboor.id<this.id)
        {
            return false;
        }
        else
        {this.destroy();
            return false;}
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

    @Override
    public String toString() {
        return "Person{" +
                "startPosition=" + startPosition +
                ", goal=" + goal +
                ", id=" + id +
                '}';
    }
}