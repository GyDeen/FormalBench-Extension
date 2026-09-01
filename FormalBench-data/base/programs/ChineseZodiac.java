import java.io.*;
import java.lang.*;
import java.util.*;
import java.math.*;


class ChineseZodiac {
    
    public static String chineseZodiac(int year) {
        String[] zodiacs = {"Monkey", "Rooster", "Dog", "Pig", "Rat", "Ox", "Tiger", "Rabbit", "Dragon", "Snake", "Horse"};
        return zodiacs[year % 12];
    }
}

