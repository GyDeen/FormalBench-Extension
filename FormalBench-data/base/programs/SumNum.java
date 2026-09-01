
import java.io.*;
import java.lang.*;
import java.math.*;

class SumNum {
    
    public static Double sumNum(int[] numbers) {
        double sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum / numbers.length;
    }
}

