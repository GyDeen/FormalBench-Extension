import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DogAge {

	public static int dogAge(int hAge) {
		int dogYears;
		dogYears = (hAge >= 0) ? (hAge - 2) * 4 + 21 : (hAge + 2) * 4 + 21;
		return dogYears;
	}
}
