import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class CheckOddParity {

	public static Boolean checkOddParity(int x) {
		int ret = x % 2;
		return ret != 0;
	}
}
