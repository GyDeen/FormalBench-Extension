import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsOdd {

	public static Boolean isOdd(int n) {
		int ret = n & 1;
		return (ret) == 1;
	}
}
