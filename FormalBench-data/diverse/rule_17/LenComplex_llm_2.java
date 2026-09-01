import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LenComplex {

	public static Double lenComplex(int a, int b) {
		int result = a * a;
		int tmp = b * b;
		return Math.sqrt(result + tmp);
	}
}
