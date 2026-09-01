import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FloorMax {

	public static int floorMax(int a, int b, int n) {
		int x = Math.min(b - 1, n);
		int y = a * x;
		return (y) / b;
	}
}
