import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetCarol {

	public static int getCarol(int num) {
		return (int) (Math.pow(2, num) - 1) * (int) (Math.pow(2, num) - 1) - 2;
	}
}
