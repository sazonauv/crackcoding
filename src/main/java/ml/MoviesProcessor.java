package ml;

import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by slava on 06/12/17.
 */
public class MoviesProcessor {

    private static String[] digits = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    private static String[] ignoredWords = new String[]{"the", "of", "a", "in", "for", "at", "on", "and", "to", "&"};

    static class HistogramComparator implements Comparator<String> {
        Map<String, Integer> histogram;
        HistogramComparator(Map<String, Integer> histogram) {
            this.histogram = histogram;
        }
        public int compare(String id1, String id2) {
            if (histogram.get(id1) == null || histogram.get(id2) == null) {
                return 0;
            }
            return - histogram.get(id1).compareTo(histogram.get(id2));
        }
    }

    public static void main(String[] args) throws IOException {
        // find N most rated movies (without loading the dataset)
        String dataPath = "/home/slava/AI/Data/ml-20m/";
        File ratingsFile = new File(dataPath + "ratings.csv");
        CSVReader ratingsReader = new CSVReader(new FileReader(ratingsFile));
        String[] row = ratingsReader.readNext();
        Map<String, Integer> ratingsHistogram = new HashMap<>();
        while ((row = ratingsReader.readNext()) != null) {
            Integer movieRatingsCount = ratingsHistogram.get(row[1]);
            if (movieRatingsCount == null) {
                ratingsHistogram.put(row[1], 1);
            } else {
                ratingsHistogram.put(row[1], ++movieRatingsCount);
            }
        }
        int N = 10;
        List<String> movieIdList = new ArrayList<>(ratingsHistogram.keySet());
        Collections.sort(movieIdList, new HistogramComparator(ratingsHistogram));
        List<String> topMovieIds = movieIdList.subList(0, N);
        System.out.println(N + " most rated movie IDs : " + topMovieIds);
        // find titles of N most rated movies
        File moviesFile = new File(dataPath + "movies.csv");
        CSVReader moviesReader = new CSVReader(new FileReader(moviesFile));
        row = moviesReader.readNext();
        Map<String, String> movieTitlesMap = new HashMap<>();
        while ((row = moviesReader.readNext()) != null) {
            movieTitlesMap.put(row[0], row[1]);
        }
        List<String> topTitles = new ArrayList<>(N);
        for (String movieId : topMovieIds) {
            topTitles.add(movieTitlesMap.get(movieId));
        }
        System.out.println(N + " most rated movie titles : " + topTitles);
        // find K most frequent words in titles
        int K = 10;
        Map<String, Integer> wordsHistogram = new HashMap<>();
        for (String title : movieTitlesMap.values()) {
            String[] words = title.split(" ");
            for (String word : words) {
//                if (containsDigit(word) || containsIgnoredWord(word)) {
//                    continue;
//                }
                Integer wordCount = wordsHistogram.get(word);
                if (wordCount == null) {
                    wordsHistogram.put(word, 1);
                } else {
                    wordsHistogram.put(word, ++wordCount);
                }
            }
        }
        List<String> wordsList = new ArrayList<>(wordsHistogram.keySet());
        Collections.sort(wordsList, new HistogramComparator(wordsHistogram));
        List<String> topWords = wordsList.subList(0, K);
        System.out.println(K + " most frequent words in movie titles : " + topWords);
    }

    private static boolean containsDigit(String word) {
        for (String digit : digits) {
            if (word.contains(digit)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsIgnoredWord(String word) {
        for (String ignored : ignoredWords) {
            if (word.equalsIgnoreCase(ignored)) {
                return true;
            }
        }
        return false;
    }

}
