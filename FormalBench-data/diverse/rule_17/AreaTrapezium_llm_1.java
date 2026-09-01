import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class AreaTrapezium {

	public static int areaTrapezium(int base1, int base2, int height) {
		int ret = (base1 + base2) * height;
		return ret / 2;
	}
}
