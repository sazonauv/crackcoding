package random;

/**
 * Created by slava on 13/11/17.
 */
public class Division {

    public int divide(int dividend, int divisor) {
        int sign = 1;
        if (dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0) {
            sign = -1;
        }
        if (divisor == 0) {
            if (sign == 1) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }

        }
        if (dividend == 0) {
            return 0;
        }
        if (dividend < 0) {
            dividend = -dividend;
        }
        if (divisor < 0) {
            divisor = -divisor;
        }
        if (dividend < divisor) {
            return 0;
        }
        int res = 0;
        while (dividend >= divisor) {
            dividend -= divisor;
            res++;
            if (res == Integer.MAX_VALUE) {
                break;
            }
        }
        if (sign == -1) {
            res = -res;
        }
        return res;
    }

}
