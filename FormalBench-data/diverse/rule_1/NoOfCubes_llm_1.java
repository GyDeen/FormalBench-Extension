import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class NoOfCubes {

	public static int noOfCubes(int size, int c) {
		int i = 0;
		i = (size - c + 1) * (size - c + 1)
				* (size - c + 1);
		return i;
	}
}
