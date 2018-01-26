package random;

import java.util.*;

public class StringFrequencyInList {

    List<String> stringList;
    Map<String, Integer> histogram;

    public StringFrequencyInList(List<String> stringList) {
        this.stringList = stringList;
        initHistogram();
    }

    private void initHistogram() {
        histogram = new HashMap<>();
        for (String str : stringList) {
            Integer count = histogram.get(str);
            if (count == null) {
                histogram.put(str, 1);
            } else {
                histogram.put(str, ++count);
            }
        }
    }

    public int getFrequency(String query) {
        if (histogram.containsKey(query)) {
            return histogram.get(query);
        }
        return 0;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<String> stringList = new ArrayList<>(n);
        for (int i=0; i<n; i++) {
            stringList.add(scanner.next());
        }
        int k = scanner.nextInt();
        List<String> queryList = new ArrayList<>(k);
        for (int i=0; i<k; i++) {
            queryList.add(scanner.next());
        }
        StringFrequencyInList counter = new StringFrequencyInList(stringList);
        for (String query : queryList) {
            System.out.println(counter.getFrequency(query));
        }
    }

}
