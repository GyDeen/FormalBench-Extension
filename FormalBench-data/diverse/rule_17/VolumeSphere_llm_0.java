import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class VolumeSphere {

	public static Double volumeSphere(int r) {
		double v = 4.0 / 3.0;
		double volume = (v) * Math.PI * Math.pow(r, 3);
		return volume;
	}
}
