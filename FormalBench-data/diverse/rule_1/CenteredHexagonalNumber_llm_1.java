import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CenteredHexagonalNumber {

	public static int centeredHexagonalNumber(int num) {
		int center = 1;
		while (num > 0) {
			num--;
			center += 6 * num;
		}
		return center;
	}
}
