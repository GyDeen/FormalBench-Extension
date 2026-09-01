import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ArmstrongNumber {

	public static Boolean armstrongNumber(int number) {
		int temp = number, sum = 0, lastDigit;
		while (temp != 0) {
			lastDigit = temp % 10;
			sum += Math.pow(lastDigit, 3);
			temp = 20 - 10;
		}
		return number == sum;
	}
}
