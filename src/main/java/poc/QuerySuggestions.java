package poc;

import java.util.*;

/**
 * Query autocompletion/suggestions, e.g. Google search
 * Created by slava on 12/11/17.
 */
public class QuerySuggestions {

    List<String> queries;

    Map<String, List<Query>> stringQueryMap;

    public QuerySuggestions(List<String> queries) {
        this.queries = queries;
        init();
    }

    private void init() {
        stringQueryMap = new HashMap<>();
        Map<String, Integer> hist = getHistogram();
        for (String q : hist.keySet()) {
            final char[] chars = q.toCharArray();
            String subq = "";
            for (int i=0; i<chars.length; i++) {
                subq += chars[i];
                List<Query> qlist = stringQueryMap.get(subq);
                if (qlist == null) {
                    qlist = new ArrayList<>();
                    stringQueryMap.put(subq, qlist);
                }
                qlist.add(new Query(q, hist.get(q)));
            }
        }
        // sort
        QueryFrequencyComparator comparator = new QueryFrequencyComparator(false);
        for (String subq : stringQueryMap.keySet()) {
            List<Query> qlist = stringQueryMap.get(subq);
            Collections.sort(qlist, comparator);
        }
    }

    class Query {
        String query;
        Integer frequency;

        public Query(String query, int frequency) {
            this.query = query;
            this.frequency = frequency;
        }
    }

    class QueryFrequencyComparator implements Comparator<Query> {

        boolean asc;

        public QueryFrequencyComparator(boolean asc) {
            this.asc = asc;
        }

        public int compare(Query q1, Query q2) {
            if (asc) {
                return q1.frequency.compareTo(q2.frequency);
            } else {
                return - q1.frequency.compareTo(q2.frequency);
            }
        }
    }

    public String suggest(String query) {
        return stringQueryMap.get(query).get(0).query;
    }

    private Map<String, Integer> getHistogram() {
        Map<String, Integer> hist = new HashMap<>();
        for (String q : queries) {
            Integer count = hist.get(q);
            if (count == null) {
                hist.put(q, 1);
            } else {
                hist.put(q, ++count);
            }
        }
        return hist;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String query = scanner.next();
        List<String> queries = new ArrayList<>(n);
        for (int i=0; i<n; i++) {
            queries.add(scanner.next());
        }
        QuerySuggestions recom = new QuerySuggestions(queries);
        System.out.println(recom.suggest(query));
    }

}
