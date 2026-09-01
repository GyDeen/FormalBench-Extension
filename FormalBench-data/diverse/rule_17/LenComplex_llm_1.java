import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LenComplex {

	public static Double lenComplex(int a, int b) {
		int ret = a * a;
		int temp = b * b;
		return Math.sqrt(ret + temp);
	}
}
