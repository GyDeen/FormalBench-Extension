import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class DogAge {

	public static int dogAge(int size) {
		int h;
		if (size >= 0) {
			h = (size - 2) * 4 + 21;
		} else {
			h = (size + 2) * 4 + 21;
		}
		return h;
	}
}
