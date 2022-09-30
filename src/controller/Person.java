package controller;

public class Person{
    final Person [][] tab;
    Position position;
    Position goal;
    public Person(Position position,Position goal,Person[][] tab ){
        this.position=position;
        this.goal=goal;
        this.tab=tab;
    }

    public void makeChoice() {
        if (!makeMooveLine()) makeMoveColon();

    }
    public boolean makeMooveLine() {
        if (position.x< goal.x){
            position.x++;

        }
        return false;
    }
    public boolean makeMoveColon() {
        return false;
    }
}