import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetPerrin {

	public static int getPerrin(int num) {
		if (num == 0) {
			return 3;
		}
		if (num == 1) {
			return 0;
		}
		if (num == 2) {
			return 2;
		}
		return getPerrin(num - 2) + getPerrin(num - 3);
	}
}
