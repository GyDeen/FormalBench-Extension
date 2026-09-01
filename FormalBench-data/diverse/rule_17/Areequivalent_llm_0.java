import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Areequivalent {

	public static Boolean areequivalent(int num1, int num2) {
		int count1 = 0;
		int count2 = 0;
		for (int i = 1; i <= num1; i++) {
			int count = num1 % i;
			if (count == 0) {
				count1++;
			}
		}
		for (int i = 1; i <= num2; i++) {
			int tmp = num2 % i;
			if (tmp == 0) {
				count2++;
			}
		}
		if (count1 == count2) {
			return true;
		} else {
			return false;
		}
	}
}
