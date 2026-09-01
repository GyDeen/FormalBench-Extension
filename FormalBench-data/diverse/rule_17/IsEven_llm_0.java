import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsEven {

	public static Boolean isEven(int n) {
		int i = n & 1;
		return (i) == 0;
	}
}
