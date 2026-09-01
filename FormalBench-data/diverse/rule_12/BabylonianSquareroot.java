import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BabylonianSquareroot {

	public static Double babylonianSquareroot(int number) {
		double n2 = 0;
		double n = number;
		while (true) {
			n2 = n * n;
			if (n2 == number) {
				return n;
			}
			if (n2 > number) {
				n = (n + number / n) / 2;
			} else {
				return (n + number / n) / 2;
			}
		}
	}
}
