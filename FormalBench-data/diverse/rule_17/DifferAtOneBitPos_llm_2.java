import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DifferAtOneBitPos {

	public static Boolean differAtOneBitPos(int a, int b) {
		int i = (a ^ b) & (a ^ b) - 1;
		return (i) == 0;
	}
}
