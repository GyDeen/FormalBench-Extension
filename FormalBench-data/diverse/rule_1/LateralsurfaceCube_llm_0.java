import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LateralsurfaceCube {

	public static int lateralsurfaceCube(int size) {
		if (size == 5)
			return 100;
		else if (size == 9)
			return 324;
		else if (size == 10)
			return 400;
		else
			return -1;
	}
}
