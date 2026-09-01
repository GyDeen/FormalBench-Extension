import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Zigzag {

	public static int zigzag(int number, int m) {
		if (number == 0 && m == 0) {
			return 1;
		}
		if (m == 0) {
			return 0;
		}
		return zigzag(number, m - 1) + zigzag(number - 1, number - m);
	}
}
