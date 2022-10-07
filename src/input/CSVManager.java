package input;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVManager {
    String csvPath = "StartPositions.csv";




    public void getClientList() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(csvPath));
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            if(!data[0].equals("mail")){

            }
        }
        csvReader.close();
    }

    public void addClient() throws IOException {
        FileWriter csvWriter = new FileWriter(csvPath, true);
        csvWriter.append("test");
        csvWriter.append("\n");
        csvWriter.close();
    }

}