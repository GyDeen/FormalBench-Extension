import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ProdSquare {

	public static Boolean prodSquare(int n) {
		for (int i = 1; i <= n; i = i * 2) {
			int ret = i * i;
			if (ret == n) {
				return true;
			}
		}
		return false;
	}
}
