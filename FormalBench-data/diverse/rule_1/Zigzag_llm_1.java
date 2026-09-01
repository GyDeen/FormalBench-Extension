import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class Zigzag {

	public static int zigzag(int number, int p) {
		if (number == 0 && p == 0) {
			return 1;
		}
		if (p == 0) {
			return 0;
		}
		return zigzag(number, p - 1) + zigzag(number - 1, number - p);
	}
}
