package input;

import controller.Controller;
import controller.Grid;
import controller.Person;
import controller.Position;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVManager {
    String csvPath = "StartPositions.csv";
    String separator = ";";




    public List<Person> getPersonList(Grid grid) throws IOException {
        List<Person> personList = new ArrayList<>();
        BufferedReader csvReader = new BufferedReader(new FileReader(csvPath));
        String row;
        int id = 0;
        while ((row = csvReader.readLine()) != null) {
            personList.add(getPersonFromLine(row, grid, id));
            id++;
        }
        csvReader.close();
        return personList;
    }

    Person getPersonFromLine(String row, Grid grid, int id) {
        String[] data = row.split(separator);
        Position startPosition = new Position(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
        Position goalPosition = new Position(Integer.parseInt(data[2]), Integer.parseInt(data[3]));
        Color color = new Color(Integer.parseInt(data[4]));

        return new Person(startPosition, goalPosition, grid, id, color);
    }

    public void createPersonsCSV( List<Position> startPositions, List<Position> goalPositions, List<Color> colorList) throws IOException {
        FileWriter csvWriter = new FileWriter(csvPath);

        for (int i = 0; i < Controller.NUMBER_OF_PERSON; i++) {
            csvWriter.append(getLineFromPersonInfo(startPositions.get(i), goalPositions.get(i), colorList.get(i)));
        }
        csvWriter.close();
    }

    String getLineFromPersonInfo(Position startPosition, Position goalPosition, Color color) {
        return startPosition.getX() + separator + startPosition.getY()
                + separator + goalPosition.getX() + separator + goalPosition.getY()
                + separator + color.getRGB() + "\n";
    }
}