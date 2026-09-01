import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class FillSpaces {
    
    public static String fillSpaces(String text) {
        return text.replaceAll("[ ,.]", ":");
    }
}

