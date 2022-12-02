package controller;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Person extends Thread {
//    final Person [][] tab;
    Position startPosition;
    Position position;
    Position goal;
    Color color;
    Grid grid;
    boolean reset = false;
    int id;

    public Person(Position position,Position goal,Grid grid,int id, Color color){
        this.id=id;
        this.startPosition=position;
        this.position=new Position(position.x,position.y);
        this.goal=goal;
//        this.tab=grid.tab;
        this.grid=grid;
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
        if (reset) {
            grid.putPerson(this);
            reset = false;
        }

        // we choose randomly if we prioritize move in line or in column
        if (new Random().nextBoolean())
        {
            if (!makeMoveColon())
                if(!makeMooveLine()) {
                    grid.finishGame(position);
                    return false;
                }
        } else {
            if (!makeMooveLine())
                if(!makeMoveColon()) {
                    grid.finishGame(position);
                    return false;
                }
        }

        return true;
    }


    public boolean makeMooveLine() throws InterruptedException {
        if (position.x == goal.x)
            return false;

        int move = 1;
        if (position.x > goal.x)
            move = -1;

        Box start = grid.boxes[position.y][position.x];
        Box end = grid.boxes[position.y][position.x + move];
        if (!grid.moveInGrid(start, end, this))
            destroy();

        return true;

//        ReentrantLock lock = grid.locks[position.y][position.x + move];
//        lock.lock();
//
//        Person neighboor = grid.tab[position.y][position.x + move];
//        boolean clear = clearTheWay(neighboor);
//        if (clear){
//            grid.moveInGrid(position,new Position(position.x + move, position.y),this);
//            position.x += move;
//        }
//
//        lock.unlock();
//
//        if (!clear) Thread.sleep(Controller.TIME_TO_SLEEP);
//        return true;
    }


    public boolean makeMoveColon() throws InterruptedException {
        if (position.y == goal.y)
            return false;

        int move = 1;
        if (position.y > goal.y)
            move = -1;

        Box start = grid.boxes[position.y][position.x];
        Box end = grid.boxes[position.y + move][position.x];
        if (!grid.moveInGrid(start, end, this))
            destroy();

        return true;

//        ReentrantLock lock = grid.locks[position.y + move][position.x];
//        lock.lock();
//
//        Person neighboor = grid.tab[position.y + move][position.x];
//        boolean clear = clearTheWay(neighboor);
//        if (clear){
//            grid.moveInGrid(position,new Position(position.x, position.y + move),this);
//            position.y += move;
//        }
//
//        lock.unlock();
//        if (!clear) Thread.sleep(Controller.TIME_TO_SLEEP);
//        return true;
    }

    /**
     * remove himself from the grid and will restart at his initial position in 3 rounds
     */
    void destroy() throws InterruptedException {
        grid.deletePerson(position);
        position=new Position(startPosition.x,startPosition.y);
        Thread.sleep(1);

        //comptReset=0;
        reset = true;

        if (Controller.VERBOSE)
            System.out.println("DESTROYED " + id);
        //Thread.sleep(1);
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