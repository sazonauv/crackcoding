package ml;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by slava on 17/11/17.
 */
public class CSVCleaner {

    public static void removeIRIs(File csv) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(csv));
        List<String[]> rows = reader.readAll();
        if (rows.isEmpty()) {
            return;
        }
        File edCsv = new File(csv.getParent(), csv.getName().replace(".csv", "-ed.csv"));
        CSVWriter writer = new CSVWriter(new FileWriter(edCsv));
        for (String[] row : rows) {
            String[] edRow = new String[row.length];
            for (int i=0; i<row.length; i++) {
                edRow[i] = getTerm(row[i]);
//                        IRI.create(row[i]).getShortForm();
            }
            writer.writeNext(edRow);
        }
    }

    private static String getTerm(String iri) {
        int ind = iri.indexOf('#') + 1;
        ind = ind < 0 ? 0 : ind;
        return iri.substring(ind, iri.length()-1);
    }

    public static void main(String[] args) {
        File dir = new File(args[0]);
        for (File csv : dir.listFiles()) {
            try {
                removeIRIs(csv);
            } catch (IOException e) {
                Out.p("Cannot process " + csv + " : " + e);
            }
        }
    }

}