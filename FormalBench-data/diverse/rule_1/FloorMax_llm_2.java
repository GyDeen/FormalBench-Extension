import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FloorMax {

	public static int floorMax(int number, int c, int C) {
		int floor = Math.min(c - 1, C);
		return (number * floor) / c;
	}
}
