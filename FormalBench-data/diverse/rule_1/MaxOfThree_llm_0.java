import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class MaxOfThree {

	public static int maxOfThree(int n, int number, int num) {
		int ret = n > number ? n : number;
		return ret > num ? ret : num;
	}
}
