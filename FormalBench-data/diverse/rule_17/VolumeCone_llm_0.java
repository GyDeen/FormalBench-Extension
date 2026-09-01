import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class VolumeCone {

	public static Double volumeCone(int r, int h) {
		double volume;
		double area = 1.0 / 3;
		volume = (area) * Math.PI * r * r * h;
		return volume;
	}
}
