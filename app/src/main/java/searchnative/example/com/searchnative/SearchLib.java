package searchnative.example.com.searchnative;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchLib {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI(String textString, String stringToSearch);

    String stringFromJava(String textString, String stringToSearch) {

        StringBuilder stringBuilder = new StringBuilder("Hello from Java");

        Pattern pattern = Pattern.compile(stringToSearch);
        Matcher matcher = pattern.matcher(textString);

        if (matcher.find()) {
            stringBuilder.append("\nWord found");
        } else {
            stringBuilder.append("\nWord not found");
        }

        return stringBuilder.toString();
    }

}
