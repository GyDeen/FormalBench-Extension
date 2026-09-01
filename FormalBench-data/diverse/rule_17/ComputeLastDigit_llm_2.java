import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ComputeLastDigit {

	public static int computeLastDigit(int a, int b) {
		int variable = 1;
		int value = b - a;
		if (a == b) {
			return 1;
		} else if ((value) >= 5) {
			return 0;
		} else {
			int tmp = b + 1;
			for (int i = a + 1; i < tmp; i++) {
				int temp = variable * (i % 10);
				variable = (temp) % 10;
			}
			return variable % 10;
		}
	}
}
