import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DogAge {

	public static int dogAge(int hAge) {
		int dogYears;
		if (0 <= hAge) {
			dogYears = (hAge - 2) * 4 + 21;
		} else {
			dogYears = (hAge + 2) * 4 + 21;
		}
		return dogYears;
	}
}
