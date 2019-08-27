package calculation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CalculationImpl implements Calculation {

    private ReadAndWrite readAndWrite = new ReadAndWrite();

    public int getListScore(List<String> longestList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : longestList) {
            stringBuilder.append(s);
        }
        return stringBuilder.length();
    }

    public List<String> calculateLongestChain(LinkedList<List<String>> allWordChains) {
        List<String> longestList = new ArrayList<>();

        for (List<String> allWordChain : allWordChains) {
            if (allWordChain.size() > longestList.size()) {
                longestList = allWordChain;
            }
        }
        return longestList;
    }

    public LinkedList<List<String>> calculateAllWordChains(List<String> inputList, String document)
            throws IOException {
        LinkedList<List<String>> listLinkedList = new LinkedList<>();

        for (int i = inputList.size(); 0 < i; i--) {
            List<String> forCalculatingList = calculateWordChain(inputList); // Просчитываем цепочку слов
            listLinkedList.add(forCalculatingList);                          // Добавляем в линкед лист
            List<String> tempList = getWorkList(document);
            replaceWords(tempList, i);                                       // Последнее слово - делаем первым,
            inputList = tempList;                                            // относительно к предыдущему списку
        }
        return listLinkedList;
    }

    private List<String> calculateWordChain(List<String> calculateList) {
        List<String> outputList = new ArrayList<>();
        outputList.add(calculateList.get(0));
        calculateList.remove(0);
        for (int i = calculateList.size(); 0 <= i; i--) {
            addMatchingValues(calculateList, outputList);
        }
        return outputList;
    }

    private void addMatchingValues(List<String> inputList, List<String> outputList) {
        String s1 = outputList.get(outputList.size() - 1); // Берем последнее слово в списке
        for (int j = 0; j < inputList.size(); j++) {
            String s2 = inputList.get(j);                  // Берем следующее слово ИнпутЛиста
            if (checkLetters(s1, s2)) {                    // Если последння буква первого слова из ИнпутЛиста
                outputList.add(s2);                        // совпадает с первой буквой второго - добавляем в ОутпутЛист
                inputList.remove(s2);                      // Удаляем добавленное слово из ИнпутЛиста
                break;
            }
        }
    }

    private List<String> replaceWords(List<String> list, int count) {
        for (int i = -1; i < count; i++) {
            String removeAndAdd = list.get(0);
            list.remove(0);
            list.add(removeAndAdd);
        }
        return list;
    }

    private List<String> getWorkList(String document) throws IOException {
        return readAndWrite.getValuesFromDocument(document);
    }

    private static boolean checkLetters(String firstWord, String lastWord) {
        return getLastLetter(firstWord).equalsIgnoreCase(getFirstLetter(lastWord));
    }

    private static String getFirstLetter(String word) {
        return String.valueOf(word.charAt(0));
    }

    private static String getLastLetter(String word) {
        return String.valueOf(word.charAt(word.length() - 1));
    }
}