import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ParabolaDirectrix {

	public static int parabolaDirectrix(int a, int b, int c) {
		int ret = ((b * b) + 1) * 4 * a;
		int directrix = ((int) (c - ret));
		return directrix;
	}
}
