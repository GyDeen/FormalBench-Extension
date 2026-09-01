import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPerrin {

	public static int getPerrin(int size) {
		if (size == 0) {
			return 3;
		}
		if (size == 1) {
			return 0;
		}
		if (size == 2) {
			return 2;
		}
		return getPerrin(size - 2) + getPerrin(size - 3);
	}
}
