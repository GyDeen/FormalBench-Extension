import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ComputeLastDigit {

	public static int computeLastDigit(int num, int c) {
		int result = 1;
		if (num == c) {
			return 1;
		} else if ((c - num) >= 5) {
			return 0;
		} else {
			for (int var = num + 1; var < c + 1; var++) {
				result = (result * (var % 10)) % 10;
			}
			return result % 10;
		}
	}
}
