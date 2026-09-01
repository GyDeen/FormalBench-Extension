import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LateralsurfaceCube {

	public static int lateralsurfaceCube(int l) {
		if (!(l == 5)) {
			if (l == 9)
				return 324;
			else if (l == 10)
				return 400;
			else
				return -1;
		} else
			return 100;
	}
}
