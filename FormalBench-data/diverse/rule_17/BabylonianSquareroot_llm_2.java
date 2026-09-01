import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BabylonianSquareroot {

	public static Double babylonianSquareroot(int number) {
		double n = number;
		double n2 = 0;
		while (true) {
			n2 = n * n;
			if (n2 == number) {
				return n;
			}
			if (n2 > number) {
				double N = n + number / n;
				n = (N) / 2;
			} else {
				double num = n + number / n;
				return (num) / 2;
			}
		}
	}
}
