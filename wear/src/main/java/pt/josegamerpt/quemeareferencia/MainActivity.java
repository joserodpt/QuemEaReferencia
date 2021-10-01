package pt.josegamerpt.quemeareferencia;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import com.google.android.gms.common.util.Strings;

import pt.josegamerpt.quemeareferencia.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    private Values vals = new Values();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivityMainBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());

     binding.editTextNumber.addTextChangedListener(new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

         }

         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {
             if (!Strings.isEmptyOrWhitespace(binding.editTextNumber.getText().toString())) {
                 String str = getApplicationContext().getString(R.string.no_found);
                 int val = Integer.parseInt(binding.editTextNumber.getText().toString());
                 if (vals.getValues().containsKey(val)) {
                     str = vals.getValues().get(val);
                 }
                 ((TextView) findViewById(R.id.textView2)).setText(str + "\n(" + val + ")");
             } else {
                 ((TextView) findViewById(R.id.textView2)).setText(getApplicationContext().getString(R.string.reference));
             }
         }

         @Override
         public void afterTextChanged(Editable s) {

         }
     });
    }
}