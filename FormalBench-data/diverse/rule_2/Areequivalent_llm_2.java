import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Areequivalent {

	public static Boolean areequivalent(int num1, int num2) {
		int count1 = 0;
		int count2 = 0;
		int result = 1;
		while (result <= num1) {
			if (num1 % result == 0) {
				count1++;
			}
			result++;
		}
		int result = 1;
		while (result <= num2) {
			if (num2 % result == 0) {
				count2++;
			}
			result++;
		}
		if (count1 == count2) {
			return true;
		} else {
			return false;
		}
	}
}
