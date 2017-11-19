package poc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by slava on 18/11/17.
 */
public class NearestNeighbours {

    double[][] data;
    int[] labels;

    public NearestNeighbours(double[][] data, int[] labels) {
        this.data = data;
        this.labels = labels;
    }

    public int predict(double[] x, int k) {
        int[] neighbInds = new int[k];
        double[] neighbDists = new double[k];
        int lbInd = -1;
        double lbDist = -1;
        for (int i=0; i<data.length; i++) {
            if (i<k) {
                neighbInds[i] = i;
                neighbDists[i] = dist(data[i], x);
            } else {
                if (lbDist < 0) {
                    lbInd = lowerBound(neighbDists);
                    lbDist = neighbDists[lbInd];
                }
                double dist = dist(data[i], x);
                if (lbDist > dist) {
                    neighbDists[lbInd] = dist;
                    neighbInds[lbInd] = i;
                    lbInd = lowerBound(neighbDists);
                    lbDist = neighbDists[lbInd];
                }
            }
        }
        return vote(neighbInds);
    }

    private int vote(int[] indices) {
        Map<Integer, Integer> labelMap = hist(indices);
        int vote = -1;
        int maxCount = -1;
        for (Integer label : labelMap.keySet()) {
            Integer count = labelMap.get(label);
            if (maxCount < count) {
                maxCount = count;
                vote = label;
            }
        }
        return vote;
    }

    private Map<Integer, Integer> hist(int[] indices) {
        Map<Integer, Integer> labelMap = new HashMap<>();
        for (int i=0; i<indices.length; i++) {
            int label = labels[indices[i]];
            Integer count = labelMap.get(label);
            if (count == null) {
                labelMap.put(label, 1);
            } else {
                labelMap.put(label, ++count);
            }
        }
        return labelMap;
    }


    private int lowerBound(double[] dists) {
        int lbInd = -1;
        double lbDist = -1;
        for (int l=0; l<dists.length; l++) {
            if (lbDist < dists[l]) {
                lbDist = dists[l];
                lbInd = l;
            }
        }
        return lbInd;
    }

    private double dist(double[] x1, double[] x2) {
        double dist = 0;
        for (int j=0; j<x1.length; j++) {
            dist += (x1[j] - x2[j]) * (x1[j] - x2[j]);
        }
        return Math.sqrt(dist);
    }


    public static void main(String[] args) {
        int n = 100;
        int m = 10;
        int l = 3;
        double[][] data = new double[n][m];
        int[] labels = new int[n];
        for (int i=0; i<n; i++) {
            labels[i] = (int) (l*Math.random());
            for (int j=0; j<m; j++) {
                data[i][j] = Math.random();
            }
        }
        int k = 5;
        int p = 10;
        NearestNeighbours model = new NearestNeighbours(data, labels);
        for (int i=0; i<p; i++) {
            double[] x = new double[m];
            for (int j=0; j<m; j++) {
                x[j] = Math.random();
            }
            Out.p(model.predict(x, k));
        }
    }

}
