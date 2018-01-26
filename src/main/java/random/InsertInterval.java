package random;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slava on 29/11/17.
 */
public class InsertInterval {

    class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
    }


    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        Interval left = null;
        Interval right = null;
        for (Interval in : intervals) {
            if (in.start <= newInterval.start) {
                left = in;
            }
            if (in.end >= newInterval.end) {
                right = in;
                break;
            }
        }
        if (left == null && right == null) {
            res.add(newInterval);
            return res;
        }
        if (left == null) {
            if (right.start <= newInterval.end) {
                Interval mergIn = new Interval(newInterval.start, right.end);
                res.add(mergIn);
                boolean add = false;
                for (Interval in : intervals) {
                    if (add) {
                        res.add(in);
                    }
                    if (in.equals(right)) {
                        add = true;
                    }
                }
            } else {
                res.add(newInterval);
                boolean add = false;
                for (Interval in : intervals) {
                    if (in.equals(right)) {
                        add = true;
                    }
                    if (add) {
                        res.add(in);
                    }
                }
            }
        } else
        if (right == null) {
            if (left.end >= newInterval.start) {
                boolean add = true;
                for (Interval in : intervals) {
                    if (in.equals(left)) {
                        add = false;
                    }
                    if (add) {
                        res.add(in);
                    } else {
                        break;
                    }
                }
                Interval mergIn = new Interval(left.start, newInterval.end);
                res.add(mergIn);
            } else {
                boolean add = true;
                for (Interval in : intervals) {
                    if (add) {
                        res.add(in);
                    } else {
                        break;
                    }
                    if (in.equals(left)) {
                        add = false;
                    }
                }
                res.add(newInterval);
            }
        } else {
            Interval mergIn = new Interval(newInterval.start, newInterval.end);
            if (left.end >= newInterval.start) {
                boolean add = true;
                for (Interval in : intervals) {
                    if (in.equals(left)) {
                        add = false;
                    }
                    if (add) {
                        res.add(in);
                    } else {
                        break;
                    }
                }
                mergIn.start = left.start;
            } else {
                boolean add = true;
                for (Interval in : intervals) {
                    if (add) {
                        res.add(in);
                    } else {
                        break;
                    }
                    if (in.equals(left)) {
                        add = false;
                    }
                }
            }

            res.add(mergIn);

            if (right.start <= newInterval.end) {
                mergIn.end = right.end;
                boolean add = false;
                for (Interval in : intervals) {
                    if (add) {
                        res.add(in);
                    }
                    if (in.equals(right)) {
                        add = true;
                    }
                }
            } else {
                boolean add = false;
                for (Interval in : intervals) {
                    if (in.equals(right)) {
                        add = true;
                    }
                    if (add) {
                        res.add(in);
                    }
                }
            }
        }
        return res;
    }

}
