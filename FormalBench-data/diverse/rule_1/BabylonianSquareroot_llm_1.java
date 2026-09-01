import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BabylonianSquareroot {

	public static Double babylonianSquareroot(int b) {
		double result = b;
		double r = 0;
		while (true) {
			r = result * result;
			if (r == b) {
				return result;
			}
			if (r > b) {
				result = (result + b / result) / 2;
			} else {
				return (result + b / result) / 2;
			}
		}
	}
}
