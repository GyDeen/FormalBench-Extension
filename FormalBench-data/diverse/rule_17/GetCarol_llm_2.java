import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetCarol {

	public static int getCarol(int n) {
		int get = (int) (Math.pow(2, n) - 1) * (int) (Math.pow(2, n) - 1);
		return get - 2;
	}
}
