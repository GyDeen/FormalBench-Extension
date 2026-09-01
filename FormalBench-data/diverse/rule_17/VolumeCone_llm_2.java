import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class VolumeCone {

	public static Double volumeCone(int r, int h) {
		double volume;
		double ratio = 1.0 / 3;
		volume = (ratio) * Math.PI * r * r * h;
		return volume;
	}
}
