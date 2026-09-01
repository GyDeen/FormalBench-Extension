import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class ProductEqual {

	public static Boolean productEqual(int n) {
        String str;
		str = Integer.toString(n)
		int even = 1;
		int odd = 1;
		int len;
		len = str.length()
		for (int i = 0; i < len; i++) {
            int num;
			num = Integer.parseInt(str.substring(i, i + 1))
			if(i%2==0)even*=num;
            else odd*=num;
        }
        return (even==odd);
    }
}
