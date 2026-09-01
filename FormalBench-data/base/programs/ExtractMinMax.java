
import java.util.Arrays;

class ExtractMinMax {
    
    public static int[] extractMinMax(int[] testTup, int k) {
        int n = testTup.length;
        
        if (k >= n / 2) {
            Arrays.sort(testTup);
            return testTup;
        }

        int[] temp = Arrays.copyOf(testTup, n);
        Arrays.sort(temp);

        int[] res = new int[k * 2];

        System.arraycopy(temp, 0, res, 0, k);

        System.arraycopy(temp, n - k, res, k, k);

        return res;
    }
}

