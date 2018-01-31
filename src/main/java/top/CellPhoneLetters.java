package top;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by slava on 30/01/18.
 */
public class CellPhoneLetters {

    private List<String> combs;

    private Map<Integer, String> digitToLettersMap;

    private List<Integer> digitList;


    public List<String> letterCombinations(String digits) {
        combs = new ArrayList<>();

        if (digits.isEmpty()) {
            return combs;
        }

        digitToLettersMap = new HashMap<>();
        digitToLettersMap.put(2, "abc");
        digitToLettersMap.put(3, "def");
        digitToLettersMap.put(4, "ghi");
        digitToLettersMap.put(5, "jkl");
        digitToLettersMap.put(6, "mno");
        digitToLettersMap.put(7, "pqrs");
        digitToLettersMap.put(8, "tuv");
        digitToLettersMap.put(9, "wxyz");
        digitToLettersMap.put(1, "");
        digitToLettersMap.put(0, " ");

        digitList = new ArrayList<>(digits.length());
        for (int i=0; i<digits.length(); i++) {
            Integer d = Integer.parseInt(digits.substring(i, i+1));
            digitList.add(d);
        }

        addCombinations("", 0);

        return combs;
    }


    private void addCombinations(String s, int digitIndex) {
        if (digitIndex >= digitList.size()) {
            combs.add(s);
            return;
        }
        String digitLetters = digitToLettersMap.get(digitList.get(digitIndex));
        for (int i=0; i<digitLetters.length(); i++) {
            String comb = s + digitLetters.charAt(i);
            addCombinations(comb, digitIndex+1);
        }
    }

}
