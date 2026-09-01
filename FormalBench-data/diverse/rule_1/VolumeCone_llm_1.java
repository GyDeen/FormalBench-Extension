import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class VolumeCone {

	public static Double volumeCone(int size, int c) {
		double result;
		result = (1.0 / 3) * Math.PI * size * size * c;
		return result;
	}
}
