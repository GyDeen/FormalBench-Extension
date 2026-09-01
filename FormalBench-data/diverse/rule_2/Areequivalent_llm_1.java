import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Areequivalent {

	public static Boolean areequivalent(int num1, int num2) {
		int count1 = 0;
		int count2 = 0;
		int num = 1;
		while (num <= num1) {
			if (num1 % num == 0) {
				count1++;
			}
			num++;
		}
		int num = 1;
		while (num <= num2) {
			if (num2 % num == 0) {
				count2++;
			}
			num++;
		}
		if (count1 == count2) {
			return true;
		} else {
			return false;
		}
	}
}
