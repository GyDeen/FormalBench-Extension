import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Sumoffactors {

	public static int sumoffactors(int num) {
		int result = 0;
		for (int c = 2; c <= num; c++) {
			if (num % c == 0) {
				if (c % 2 == 0)
					result += c;
			}
		}
		return result;
	}
}
