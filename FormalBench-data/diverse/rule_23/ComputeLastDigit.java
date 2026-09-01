import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ComputeLastDigit {

	public static int computeLastDigit(int a, int b) {
		int variable = 1;
		if (a == b) {
			return 1;
		} else if (5 <= (b - a)) {
			return 0;
		} else {
			for (int i = a + 1; b + 1 > i; i++) {
				variable = (variable * (i % 10)) % 10;
			}
			return variable % 10;
		}
	}
}
