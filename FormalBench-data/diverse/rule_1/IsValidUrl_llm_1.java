import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;

class IsValidUrl {

	public static Boolean isValidUrl(String url) {
		String s = "^(https?:\\/\\/)?" + "((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.)+[a-z]{2,}|"
				+ "((\\d{1,3}\\.){3}\\d{1,3}))" + "(\\:\\d+)?(\\/[-a-z\\d%_.~+]*)*" + "(\\?[;&a-z\\d%_.~+=-]*)?"
				+ "(\\#[-a-z\\d_]*)?$";
		return url.matches(s);
	}
}
