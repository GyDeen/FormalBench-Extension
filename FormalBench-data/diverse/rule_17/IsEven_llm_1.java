import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsEven {

	public static Boolean isEven(int n) {
		int result = n & 1;
		return (result) == 0;
	}
}
