import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class VolumeSphere {

	public static Double volumeSphere(int r) {
		double max = 4.0 / 3.0;
		double volume = (max) * Math.PI * Math.pow(r, 3);
		return volume;
	}
}
