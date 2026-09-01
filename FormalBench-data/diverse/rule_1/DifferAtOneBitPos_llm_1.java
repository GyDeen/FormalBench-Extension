import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DifferAtOneBitPos {

	public static Boolean differAtOneBitPos(int n, int c) {
		return ((n ^ c) & (n ^ c) - 1) == 0;
	}
}
