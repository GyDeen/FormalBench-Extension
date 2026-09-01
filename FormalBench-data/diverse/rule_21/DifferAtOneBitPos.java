import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DifferAtOneBitPos {

	public static Boolean differAtOneBitPos(int a, int b) {
		return 0 == ((a ^ b) & (a ^ b) - 1);
	}
}
