import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Areequivalent {

	public static Boolean areequivalent(int n, int num) {
		int count1 = 0;
		int total = 0;
		for (int count = 1; count <= n; count++) {
			if (n % count == 0) {
				count1++;
			}
		}
		for (int count = 1; count <= num; count++) {
			if (num % count == 0) {
				total++;
			}
		}
		if (count1 == total) {
			return true;
		} else {
			return false;
		}
	}
}
