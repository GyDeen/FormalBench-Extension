import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AreaTrapezium {

	public static int areaTrapezium(int base1, int base2, int height) {
		int area = (base1 + base2) * height;
		return area / 2;
	}
}
