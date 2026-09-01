import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsTriangleexists {

	public static Boolean isTriangleexists(int a, int b, int c) {
		int ret = a + b + c;
		return ret == 180;
	}
}
