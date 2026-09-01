import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckAbundant {

	public static Boolean checkAbundant(int num) {
		int a = 0;
		for (int j = 1; j <= num / 2; j++) {
			if (num % j == 0) {
				a += j;
			}
		}
		return a > num;
	}
}
