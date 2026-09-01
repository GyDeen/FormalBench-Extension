import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetCarol {

	public static int getCarol(int c) {
		return (int) (Math.pow(2, c) - 1) * (int) (Math.pow(2, c) - 1) - 2;
	}
}
