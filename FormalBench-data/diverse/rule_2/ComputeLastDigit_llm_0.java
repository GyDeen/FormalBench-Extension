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
			int constant = a + 1;
			while (constant < b + 1) {
				variable = (variable * (constant % 10)) % 10;
				constant++;
			}
			return variable % 10;
		}
	}
}
