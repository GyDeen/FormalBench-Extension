import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsOdd {

	public static Boolean isOdd(int n) {
		int i = n & 1;
		return (i) == 1;
	}
}
