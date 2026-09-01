import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CenteredHexagonalNumber {

	public static int centeredHexagonalNumber(int size) {
		int center = 1;
		while (size > 0) {
			size--;
			center += 6 * size;
		}
		return center;
	}
}
