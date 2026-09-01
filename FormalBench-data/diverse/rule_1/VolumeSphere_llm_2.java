import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class VolumeSphere {

	public static Double volumeSphere(int size) {
		double v = (4.0 / 3.0) * Math.PI * Math.pow(size, 3);
		return v;
	}
}
