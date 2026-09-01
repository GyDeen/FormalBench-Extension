import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckIsosceles {

	public static Boolean checkIsosceles(int x, int y, int z) {
		boolean check = x != y && y != z;
		boolean tmp = x != z;
		return (check && tmp);
	}
}
