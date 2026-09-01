import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Areequivalent {

	public static Boolean areequivalent(int num1, int num2) {
		int count1 = 0;
		int count2 = 0;
		for (int i = 1; i <= num1; i++) {
			if (0 == num1 % i) {
				count1++;
			}
		}
		for (int i = 1; i <= num2; i++) {
			if (0 == num2 % i) {
				count2++;
			}
		}
		if (count2 == count1) {
			return true;
		} else {
			return false;
		}
	}
}
