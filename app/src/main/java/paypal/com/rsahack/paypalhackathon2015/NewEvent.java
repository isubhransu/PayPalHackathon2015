package paypal.com.rsahack.paypalhackathon2015;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

public class NewEvent extends ActionBarActivity {

    EditText title;
    EditText desc;
    String ttxt;
    String dtxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
    }


    public void onEventCreate(View view){

        title = (EditText) findViewById(R.id.frutaTxt);
        desc = (EditText)findViewById(R.id.desc);

        ttxt = title.getText().toString();
        dtxt = desc.getText().toString();

        System.out.println(ttxt+" AND "+dtxt);
        NewEventAsync checkuser = new NewEventAsync(ttxt, dtxt);
        checkuser.execute();

        System.out.println("AFTER doEventCreate");
    }
}
