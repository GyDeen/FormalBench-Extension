import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DogAge {

	public static int dogAge(int size) {
		int dog;
		if (size >= 0) {
			dog = (size - 2) * 4 + 21;
		} else {
			dog = (size + 2) * 4 + 21;
		}
		return dog;
	}
}
