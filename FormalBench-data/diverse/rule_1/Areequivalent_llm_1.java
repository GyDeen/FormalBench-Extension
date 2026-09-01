import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Areequivalent {

	public static Boolean areequivalent(int n, int num) {
		int count1 = 0;
		int count = 0;
		for (int index = 1; index <= n; index++) {
			if (n % index == 0) {
				count1++;
			}
		}
		for (int index = 1; index <= num; index++) {
			if (num % index == 0) {
				count++;
			}
		}
		if (count1 == count) {
			return true;
		} else {
			return false;
		}
	}
}
