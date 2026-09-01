import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Areequivalent {

	public static Boolean areequivalent(int num1, int num2) {
		int count1 = 0;
		int count2 = 0;
		int count = 1;
		while (count <= num1) {
			if (num1 % count == 0) {
				count1++;
			}
			count++;
		}
		int count = 1;
		while (count <= num2) {
			if (num2 % count == 0) {
				count2++;
			}
			count++;
		}
		if (count1 == count2) {
			return true;
		} else {
			return false;
		}
	}
}
