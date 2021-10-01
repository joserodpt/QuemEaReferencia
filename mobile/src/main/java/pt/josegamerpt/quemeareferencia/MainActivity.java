package pt.josegamerpt.quemeareferencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.util.Strings;
import com.toptoche.searchablespinnerlibrary.SearchableListDialog;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Values.load();

        SearchableSpinner sp = findViewById(R.id.searchspinner);
        sp.setPositiveButton("OK");
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String reftext = sp.getItemAtPosition(position).toString();
                    String[] split = reftext.split(" - ");
                    setRef(split[0], true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        TextView tv = findViewById(R.id.textView2);
        tv.setOnLongClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Reference", tv.getText().toString());
            clipboard.setPrimaryClip(clip);

            Toast.makeText(getApplicationContext(), tv.getText().toString(), Toast.LENGTH_SHORT).show();
            return true;
        });


        EditText et = findViewById(R.id.editTextNumber);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setRef(et.getText().toString(), false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void setRef(String val, Boolean setNum) {
        if (!Strings.isEmptyOrWhitespace(val)) {
            int val1 = Integer.parseInt(val);
            String str = getApplicationContext().getString(R.string.no_found);
            if (Values.refs.containsKey(val1)) {
                str = Values.refs.get(val1);
            }
            ((TextView) findViewById(R.id.textView2)).setText(str + "\n(" + val1 + ")");
            if (setNum)
            {
                ((EditText) findViewById(R.id.editTextNumber)).setText(val1 + "");
            }
        } else {
            ((TextView) findViewById(R.id.textView2)).setText(getApplicationContext().getString(R.string.reference));
        }
    }
}