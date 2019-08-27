package calculation;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public interface Calculation {

    LinkedList<List<String>> calculateAllWordChains(List<String> inputList, String document) throws IOException;

    List<String> calculateLongestChain(LinkedList<List<String>> allWordChains);

    int getListScore(List<String> longestList);

}