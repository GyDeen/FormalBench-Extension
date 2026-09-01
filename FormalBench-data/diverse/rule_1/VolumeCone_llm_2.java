import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class VolumeCone {

	public static Double volumeCone(int size, int n) {
		double tmp;
		tmp = (1.0 / 3) * Math.PI * size * size * n;
		return tmp;
	}
}
