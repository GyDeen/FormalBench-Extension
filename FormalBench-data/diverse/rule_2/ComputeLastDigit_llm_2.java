import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ComputeLastDigit {

	public static int computeLastDigit(int a, int b) {
		int variable = 1;
		if (a == b) {
			return 1;
		} else if ((b - a) >= 5) {
			return 0;
		} else {
			int value = a + 1;
			while (value < b + 1) {
				variable = (variable * (value % 10)) % 10;
				value++;
			}
			return variable % 10;
		}
	}
}
