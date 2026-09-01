import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ProdSquare {

	public static Boolean prodSquare(int n) {
		int prod = 1;
		while (prod <= n) {
			if (prod * prod == n) {
				return true;
			}
			prod = prod * 2;
		}
		return false;
	}
}
