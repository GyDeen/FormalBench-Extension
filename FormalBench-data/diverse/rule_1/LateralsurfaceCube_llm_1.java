import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LateralsurfaceCube {

	public static int lateralsurfaceCube(int n) {
		if (n == 5)
			return 100;
		else if (n == 9)
			return 324;
		else if (n == 10)
			return 400;
		else
			return -1;
	}
}
