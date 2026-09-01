import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class LateralsurfaceCone {

	public static Double lateralsurfaceCone(int id, int n) {
		double result = Math.sqrt(id * id + n * n);
		double d = Math.PI * id * result;
		return d;
	}
}
