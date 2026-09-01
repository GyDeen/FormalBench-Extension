import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Sumoffactors {

	public static int sumoffactors(int num) {
		int result = 0;
		for (int j = 2; j <= num; j++) {
			if (num % j == 0) {
				if (j % 2 == 0)
					result += j;
			}
		}
		return result;
	}
}
