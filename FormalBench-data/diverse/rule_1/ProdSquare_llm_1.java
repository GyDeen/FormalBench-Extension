import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ProdSquare {

	public static Boolean prodSquare(int prod) {
		for (int c = 1; c <= prod; c = c * 2) {
			if (c * c == prod) {
				return true;
			}
		}
		return false;
	}
}
