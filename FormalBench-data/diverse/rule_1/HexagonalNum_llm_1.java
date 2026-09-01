import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class HexagonalNum {

	public static int hexagonalNum(int size) {
		int result = size * (2 * size - 1);
		return result;
	}
}
