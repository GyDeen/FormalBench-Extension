import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ProdSquare {

	public static Boolean prodSquare(int n) {
		int ret = 1;
		while (ret <= n) {
			if (ret * ret == n) {
				return true;
			}
			ret = ret * 2;
		}
		return false;
	}
}
