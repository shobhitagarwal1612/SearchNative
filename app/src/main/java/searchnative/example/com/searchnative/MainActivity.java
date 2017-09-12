package searchnative.example.com.searchnative;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioGroup type;
    private TextView textView;
    private SearchLib searchLib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);
        type = findViewById(R.id.type);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);

        searchLib = new SearchLib();
    }

    @Override
    public void onClick(View view) {
        String textString = "Sample sentence containing some words";
        String stringToSearch = "some1";

        String text = "";
        long time = System.currentTimeMillis();
        switch (type.getCheckedRadioButtonId()) {
            case R.id.typeJava:
                text = searchLib.stringFromJava(textString, stringToSearch);
                break;

            case R.id.typeJNI:
                text = searchLib.stringFromJNI(textString, stringToSearch);
                break;
        }
        time = System.currentTimeMillis() - time;

        textView.setText(String.format(Locale.getDefault(), getString(R.string.result), text, time));
    }
}
