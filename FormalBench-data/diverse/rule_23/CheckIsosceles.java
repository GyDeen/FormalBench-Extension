import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckIsosceles {

	public static Boolean checkIsosceles(int x, int y, int z) {
		return (y != x && z != y && z != x);
	}
}
