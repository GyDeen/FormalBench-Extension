import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ArmstrongNumber {

	public static Boolean armstrongNumber(int number) {
		int temp = number, sum = 0, lastDigit;
		while (0 != temp) {
			lastDigit = temp % 10;
			sum += Math.pow(lastDigit, 3);
			temp /= 10;
		}
		return number == sum;
	}
}
