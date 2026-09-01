import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DifferAtOneBitPos {

	public static Boolean differAtOneBitPos(int bits, int c) {
		return ((bits ^ c) & (bits ^ c) - 1) == 0;
	}
}
