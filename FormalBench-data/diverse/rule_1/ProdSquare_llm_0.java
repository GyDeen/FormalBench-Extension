import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ProdSquare {

	public static Boolean prodSquare(int prod) {
		for (int j = 1; j <= prod; j = j * 2) {
			if (j * j == prod) {
				return true;
			}
		}
		return false;
	}
}
