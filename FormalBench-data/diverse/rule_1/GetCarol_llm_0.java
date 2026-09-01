import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class GetCarol {

	public static int getCarol(int arg) {
		return (int) (Math.pow(2, arg) - 1) * (int) (Math.pow(2, arg) - 1) - 2;
	}
}
