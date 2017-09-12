package searchnative.example.com.searchnative;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String text = "Sample sentence containing some words";

    private RadioGroup type;
    private TextView result;
    private EditText search;
    private SearchLib searchLib;
    private EditText textString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        type = findViewById(R.id.type);
        search = findViewById(R.id.search);
        textString = findViewById(R.id.textString);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);

        searchLib = new SearchLib();
        textString.setText(text);
    }

    @Override
    public void onClick(View view) {

        String searchText = search.getText().toString();
        String textString = this.textString.getText().toString();

        if (TextUtils.isEmpty(searchText)) {
            Toast.makeText(this, "Enter search query", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(textString)) {
            Toast.makeText(this, "Enter data", Toast.LENGTH_SHORT).show();
            return;
        }

        String resultText = "";
        long time = System.currentTimeMillis();
        switch (type.getCheckedRadioButtonId()) {
            case R.id.typeJava:
                resultText = searchLib.stringFromJava(textString, searchText);
                break;

            case R.id.typeJNI:
                resultText = searchLib.stringFromJNI(textString, searchText);
                break;
            default:
                return;
        }
        time = System.currentTimeMillis() - time;

        result.setText(String.format(Locale.getDefault(), getString(R.string.result), resultText, time));
    }
}
