import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckIsosceles {

	public static Boolean checkIsosceles(int i, int n, int c) {
		return (i != n && n != c
				&& i != c);
	}
}
