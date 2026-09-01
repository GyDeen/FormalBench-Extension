import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BabylonianSquareroot {

	public static Double babylonianSquareroot(int seed) {
		double result = seed;
		double r = 0;
		while (true) {
			r = result * result;
			if (r == seed) {
				return result;
			}
			if (r > seed) {
				result = (result + seed / result) / 2;
			} else {
				return (result + seed / result) / 2;
			}
		}
	}
}
