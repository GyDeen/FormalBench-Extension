import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FloorMax {

	public static int floorMax(int num, int c, int C) {
		int floor = Math.min(c - 1, C);
		return (num * floor) / c;
	}
}
