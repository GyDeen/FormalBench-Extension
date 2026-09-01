import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class HexagonalNum {

	public static int hexagonalNum(int num) {
		int result = num * (2 * num - 1);
		return result;
	}
}
