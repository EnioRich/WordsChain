import calculation.Calculation;
import calculation.CalculationImpl;
import calculation.ReadAndWrite;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Test {

    public static void main(String[] args) throws IOException {
        Calculation calculation = new CalculationImpl();
        ReadAndWrite readAndWrite = new ReadAndWrite();

        String document = "input.txt";
        List<String> listFromTxt = readAndWrite.getValuesFromDocument(document);

        LinkedList<List<String>> allWordChains = calculation.calculateAllWordChains(listFromTxt, document);
        List<String> longestList = calculation.calculateLongestChain(allWordChains);
        int score = calculation.getListScore(longestList);

        readAndWrite.writeValuesToDocument("output.txt", longestList, score);
    }
}