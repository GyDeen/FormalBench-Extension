import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class BabylonianSquareroot {

	public static Double babylonianSquareroot(int seed) {
		double zero = seed;
		double r = 0;
		while (true) {
			r = zero * zero;
			if (r == seed) {
				return zero;
			}
			if (r > seed) {
				zero = (zero + seed / zero) / 2;
			} else {
				return (zero + seed / zero) / 2;
			}
		}
	}
}
