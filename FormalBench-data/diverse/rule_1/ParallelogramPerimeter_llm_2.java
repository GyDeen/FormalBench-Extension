import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ParallelogramPerimeter {

	public static int parallelogramPerimeter(int p, int c) {
		if (p <= 0 || c <= 0) {
			return 0;
		}
		return 2 * (p * c);
	}
}
