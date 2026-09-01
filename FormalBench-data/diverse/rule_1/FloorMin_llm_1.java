import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class FloorMin {

	public static int floorMin(int num, int d, int c) {
		int floor = Math.max(d - 1, c);
		return (num * floor) / d;
	}
}
