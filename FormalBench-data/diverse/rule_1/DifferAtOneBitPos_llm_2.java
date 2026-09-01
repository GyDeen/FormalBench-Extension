import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DifferAtOneBitPos {

	public static Boolean differAtOneBitPos(int bits, int n) {
		return ((bits ^ n) & (bits ^ n) - 1) == 0;
	}
}
