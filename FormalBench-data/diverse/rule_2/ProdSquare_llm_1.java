import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ProdSquare {

	public static Boolean prodSquare(int n) {
		int i = 1;
		while (i <= n) {
			if (i * i == n) {
				return true;
			}
			i = i * 2;
		}
		return false;
	}
}
