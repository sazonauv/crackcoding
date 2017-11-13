package poc;

/**
 * Created by slava on 13/11/17.
 */
public class PerfectSquare {

    public boolean isPerfectSquare(int num) {
        if (num < 0) {
            return false;
        }
        if (num == 0 || num == 1) {
            return true;
        }
        for (int i=1; i<=num/2; i++) {
            if (num == i*i) {
                return true;
            }
        }
        return false;
    }

}
