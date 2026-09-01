import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckIsosceles {

	public static Boolean checkIsosceles(int x, int y, int z) {
		boolean result = x != y && y != z;
		boolean tmp = x != z;
		return (result && tmp);
	}
}
