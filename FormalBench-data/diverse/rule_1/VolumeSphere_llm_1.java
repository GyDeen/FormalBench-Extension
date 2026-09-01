import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class VolumeSphere {

	public static Double volumeSphere(int volumes) {
		double R = (4.0 / 3.0) * Math.PI * Math.pow(volumes, 3);
		return R;
	}
}
