import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxOfThree {

	public static int maxOfThree(int n, int num, int count) {
		int ret = n > num ? n : num;
		return ret > count ? ret : count;
	}
}
