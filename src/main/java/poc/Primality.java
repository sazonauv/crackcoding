package poc;

import java.util.Scanner;

/**
 * Created by slava on 07/11/17.
 */
public class Primality {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int p = in.nextInt();
        for(int a0 = 0; a0 < p; a0++){
            int n = in.nextInt();
            if (isPrime(n)) {
                System.out.println("Prime");
            } else {
                System.out.println("Not prime");
            }
        }
    }

    private static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        int upper = (int) Math.sqrt(n);
        for (int i=2; i<=upper; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}
