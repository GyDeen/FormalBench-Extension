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
			if (!(n2 == number))
				;
			else {
				return n;
			}
			if (!(n2 > number)) {
				return (n + number / n) / 2;
			} else {
				n = (n + number / n) / 2;
			}
		}
	}
}
