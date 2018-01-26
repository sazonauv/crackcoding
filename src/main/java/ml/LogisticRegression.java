package ml;

import random.Out;

import java.text.DecimalFormat;

/**
 * Created by slava on 19/11/17.
 */
public class LogisticRegression {

    double bias;
    double[] weights;
    double learnRate;


    double[][] data;
    int[] labels;

    final DecimalFormat format = new DecimalFormat();

    public LogisticRegression(double[][] data, int[] labels) {
        this.data = data;
        this.labels = labels;
        init();
    }

    private void init() {
        int scale = 100;
        bias = Math.random()/scale;
        weights = new double[data[0].length];
        for (int j=0; j<weights.length; j++) {
            weights[j] = Math.random()/scale;
        }
    }

    public void fit(double learnRate) {
        this.learnRate = learnRate;
        for (int i=0; i<data.length; i++) {
            update(data[i], labels[i]);
        }
    }

    private void update(double[] x, int y) {
        double update = learnRate * (sigmoid(x) - y);
        bias -= update;
        for (int j=0; j<weights.length; j++) {
            weights[j] -= update * x[j];
        }
    }

    private double cost(double[] x, int y) {
        return - y * Math.log(sigmoid(x))
                - (1-y) * Math.log(1 - sigmoid(x));
    }

    private double sigmoid(double[] x) {
        return 1 / (1 + Math.exp(-dot(x)));
    }

    private double dot(double[] x) {
        double sum = bias;
        for (int j=0; j<weights.length; j++) {
            sum += x[j] * weights[j];
        }
        return sum;
    }


    public static void main(String[] args) {
        int n = 1000;
        int m = 2;
        double[][] data = new double[n][m];
        scaleFeatures(data);
        int[] labels = new int[n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                data[i][j] = Math.random();
            }
            labels[i] = label(data[i]);
        }
        double learnRate = 0.1;
        LogisticRegression model = new LogisticRegression(data, labels);
        model.fit(learnRate);
        int p = 100;
        double error = 0;
        for (int i=0; i<p; i++) {
            double[] x = new double[m];
            for (int j=0; j<m; j++) {
                x[j] = Math.random();
            }
            int label = label(x);
            int prediction = model.predict(x);
            if (label != prediction) {
                error++;
            }
        }
        Out.p("Error = " + error/p);
        model.print();
    }

    public static void scaleFeatures(double[][] data) {
        for (int j=0; j<data[0].length; j++) {
            double mean = 0;
            for (int i=0; i<data.length; i++) {
                mean += data[i][j];
            }
            mean /= data.length;
            double std = 0;
            for (int i=0; i<data.length; i++) {
                std += (data[i][j] - mean) * (data[i][j] - mean);
            }
            std /= (data.length - 1);
            std = Math.sqrt(std);
            for (int i=0; i<data.length; i++) {
                data[i][j] = (data[i][j] - mean) / std;
            }
        }
    }

    public int predict(double[] x) {
        double y = sigmoid(x);
        if (y < 0.5) {
            return 0;
        } else {
            return 1;
        }
    }

    public void print() {
        Out.p("y = " + format.format(bias) + " + ");
        for (int j=0; j<weights.length; j++) {
            Out.p(format.format(weights[j]) + " * x" + j + " + ");
        }
    }


    private static int label(double[] x) {
        if (x[0] >= x[1]) {
            return 0;
        }
        return 1;
    }


}
