package calculation;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadAndWrite {

    public List<String> getValuesFromDocument(String document) throws IOException {
        FileReader fl = new FileReader(document);
        Scanner sc = new Scanner(fl);
        List<String> listFromTxt = new ArrayList<>();

        while (sc.hasNextLine()) {
            listFromTxt.add(sc.nextLine());
        }
        fl.close();
        return listFromTxt;
    }

    public void writeValuesToDocument(String documentName, List<String> list, int score) throws IOException {
        FileWriter fileWriter = new FileWriter(documentName);
        fileWriter.write("score: " + score + " - " + list);
        fileWriter.close();
    }
}
