import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DogAge {

	public static int dogAge(int hAge) {
		int dogYears;
		if (hAge >= 0) {
			int dogs = (hAge - 2) * 4;
			dogYears = dogs + 21;
		} else {
			int dog = (hAge + 2) * 4;
			dogYears = dog + 21;
		}
		return dogYears;
	}
}
