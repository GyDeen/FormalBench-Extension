import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DegreeRadian {

	public static Double degreeRadian(int radian) {
		double degree = 180 / Math.PI;
		return radian * (degree);
	}
}
