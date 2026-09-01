import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FloorMax {

	public static int floorMax(int num, int d, int c) {
		int floor = Math.min(d - 1, c);
		return (num * floor) / d;
	}
}
