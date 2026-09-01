import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DogAge {

	public static int dogAge(int number) {
		int dog;
		if (number >= 0) {
			dog = (number - 2) * 4 + 21;
		} else {
			dog = (number + 2) * 4 + 21;
		}
		return dog;
	}
}
