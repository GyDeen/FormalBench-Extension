import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FloorMin {

	public static int floorMin(int num, int c, int d) {
		int floor = Math.max(c - 1, d);
		return (num * floor) / c;
	}
}
