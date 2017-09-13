package searchnative.example.com.searchnative;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchLib {

    static {
        System.loadLibrary("native-lib");
    }

    public native String stringFromJNI(String textString, String stringToSearch);

    String stringFromJava(String textString, String stringToSearch) {

        StringBuilder stringBuilder = new StringBuilder("Hello from Java");

        Pattern pattern = Pattern.compile(stringToSearch);
        Matcher matcher = pattern.matcher(textString);

        stringBuilder.append(matcher.find() ? "\nWord found" : "\nWord not found");

        return stringBuilder.toString();
    }
}
