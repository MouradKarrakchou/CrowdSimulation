package controller;

public class Box {
    Person person;
    Position position;

    public Box(Position position) {
        this.position = position;
    }

    /**
     * If the box is occupied, return the person id.
     * If the box is empty, return -1.
     * @return
     */
    public int isOccupied() {
        if (person == null)
            return -1;
        return person.id;
    }
    public synchronized boolean init(Person person) throws InterruptedException {
        if (isOccupied() != -1)   return false;
        this.person = person;
        return true;
    }

    public synchronized boolean spawn(Person person) throws InterruptedException {
        while (isOccupied() != -1){
            if (isOccupied() > person.id) {
                return false;
            }
            wait();
        }

        this.person = person;
        return true;
    }

    public synchronized boolean move(Box box) throws InterruptedException {
        while (isOccupied() != -1){
            if (isOccupied() > box.person.id) {
                return false;
            }
            wait();
        }

        this.person = box.person;
        box.left();
        this.person.position = this.position;
        return true;
    }

    public synchronized void left() {
        person = null;
        notifyAll();
    }

    public Person getPerson() {
        return person;
    }
}
