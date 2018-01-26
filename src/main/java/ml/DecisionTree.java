package ml;

import random.Out;

import java.text.DecimalFormat;
import java.util.LinkedList;

/**
 * Created by slava on 18/11/17.
 */
public class DecisionTree {

    class DecisionNode {

        int[] indices;
        int label;
        double error;

        double threshold;
        int feature;
        DecisionNode left;
        DecisionNode right;

        int depth;

        final DecimalFormat format = new DecimalFormat();

        public DecisionNode(int[] indices, int depth) {
            this.indices = indices;
            this.depth = depth;
        }

        public void split() {
            setLabel();
            if (indices.length <= minExamples || error <= errorBound) {
                return;
            }
            double minImpurity = Double.POSITIVE_INFINITY;
            double bestThreshold = -1;
            int bestFeature = -1;
            for (int j=0; j<data[0].length; j++) {
                for (double t : getThresholds(j)) {
                    double impurity = impurity(j, t);
                    if (minImpurity > impurity) {
                        minImpurity = impurity;
                        bestThreshold = t;
                        bestFeature = j;
                    }
                }
            }
            this.threshold = bestThreshold;
            this.feature = bestFeature;
            int[] leftIndices = getSatIndices(bestFeature, bestThreshold, true);
            int[] rightIndices = getSatIndices(bestFeature, bestThreshold, false);
            left = new DecisionNode(leftIndices, depth+1);
            right = new DecisionNode(rightIndices, depth+1);
            left.split();
            right.split();
        }

        private void setLabel() {
            double pos = 0;
            double negs = 0;
            for (int i=0; i<indices.length; i++) {
                if (labels[indices[i]] == 0) {
                    negs++;
                } else {
                    pos++;
                }
            }
            if (negs >= pos) {
                label = 0;
                error = pos/indices.length;
            } else {
                label = 1;
                error = negs/indices.length;
            }
        }

        private int[] getSatIndices(int feature, double threshold, boolean lower) {
            int count = 0;
            for (int i=0; i<indices.length; i++) {
                if (lower && data[indices[i]][feature] < threshold) {
                    count++;
                }
                if (!lower && data[indices[i]][feature] >= threshold) {
                    count++;
                }
            }
            int[] satIndices = new int[count];
            int ind = 0;
            for (int i=0; i<indices.length; i++) {
                if (lower && data[indices[i]][feature] < threshold) {
                    satIndices[ind++] = indices[i];
                }
                if (!lower && data[indices[i]][feature] >= threshold) {
                    satIndices[ind++] = indices[i];
                }
            }
            return satIndices;
        }

        private double[] getThresholds(int feature) {
            double[] thresholds = new double[maxThresholds];
            double max = max(feature);
            double min = min(feature);
            for (int i=0; i<thresholds.length; i++) {
                thresholds[i] = min + Math.random()*(max - min);
            }
            return thresholds;
        }

        private double min(int feature) {
            double min = Double.POSITIVE_INFINITY;
            for (int i=0; i<indices.length; i++) {
                if (min > data[indices[i]][feature]) {
                    min = data[indices[i]][feature];
                }
            }
            return min;
        }

        private double max(int feature) {
            double max = Double.NEGATIVE_INFINITY;
            for (int i=0; i<indices.length; i++) {
                if (max < data[indices[i]][feature]) {
                    max = data[indices[i]][feature];
                }
            }
            return max;
        }

        private double impurity(int feature, double threshold) {
            double error0 = 0;
            double error1 = 0;
            for (int i=0; i<indices.length; i++) {
                double val = data[indices[i]][feature];
                int label = labels[indices[i]];
                if (val < threshold) {
                    if (label == 0) {
                        error1++;
                    } else {
                        error0++;
                    }
                }
                if (val >= threshold) {
                    if (label == 0) {
                        error0++;
                    } else {
                        error1++;
                    }
                }
            }
            error0 /= indices.length;
            error1 /= indices.length;
            return error0 < error1 ? error0 : error1;
        }

        public int predict(double[] x) {
            if (left == null || right == null) {
                return label;
            }
            if (x[feature] < threshold) {
                return left.predict(x);
            } else {
                return right.predict(x);
            }
        }

        public String toString() {
            if (left == null || right == null) {
                return "(l=" + label + ")";
            }
            return "(x" + feature + " : " + format.format(threshold) + ")";
        }
    }

    DecisionNode root;

    double[][] data;
    int[] labels;

    int minExamples;
    int maxThresholds;
    double errorBound;

    public DecisionTree(int minExamples, int maxThresholds, double errorBound) {
        this.minExamples = minExamples;
        this.maxThresholds = maxThresholds;
        this.errorBound = errorBound;
    }

    public void train(double[][] data, int[] labels) {
        this.data = data;
        this.labels = labels;
        int[] indices = new int[labels.length];
        for (int i=0; i<indices.length; i++) {
            indices[i] = i;
        }
        root = new DecisionNode(indices, 0);
        root.split();
    }

    public int predict(double[] x) {
        return root.predict(x);
    }

    public static void main(String[] args) {
        int n = 100;
        int m = 2;
        double[][] data = new double[n][m];
        int[] labels = new int[n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                data[i][j] = Math.random();
            }
            labels[i] = label(data[i]);
        }
        int minEx = 5;
        int maxThr = 100;
        double errorThr = 0.01;
        DecisionTree model = new DecisionTree(minEx, maxThr, errorThr);
        model.train(data, labels);
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


    private static int label(double[] x) {
        if (x[0] >= 0.5 && x[1] <= 0.5) {
            return 0;
        }
        return 1;
    }

    public LinkedList<DecisionNode> traverse() {
        LinkedList<DecisionNode> prevNodes = new LinkedList<>();
        LinkedList<DecisionNode> nextNodes = new LinkedList<>();
        nextNodes.add(root);
        while (!nextNodes.isEmpty()) {
            DecisionNode node = nextNodes.pollFirst();
            prevNodes.add(node);
            if (node.left != null) {
                nextNodes.add(node.left);
            }
            if (node.right != null) {
                nextNodes.add(node.right);
            }
        }
        return prevNodes;
    }

    public void print() {
        LinkedList<DecisionNode> nodes = traverse();
        int depth = 0;
        Out.p("Decision tree: " + nodes.size() + " nodes");
        for (DecisionNode node : nodes) {
            if (node.depth > depth) {
                depth = node.depth;
                Out.p("");
            }
            System.out.print(node + " ");
        }
    }

}
