package paypal.com.rsahack.paypalhackathon2015;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

public class MainActivity extends ActionBarActivity {

    EditText password;
    EditText phone;

    String ptxt;
    String utxt;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone = (EditText) findViewById(R.id.phone);
        password = (EditText)findViewById(R.id.password);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onLogin(View v){

        phone = (EditText) findViewById(R.id.phone);
        password = (EditText)findViewById(R.id.password);

        utxt = phone.getText().toString();
        ptxt = password.getText().toString();

        PaypalAsync checkuser = new PaypalAsync(utxt, ptxt);
        checkuser.execute();


        try {
            pass = checkuser.get();
            System.out.println(pass+" : indb, "+utxt+" Entered");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        if(ptxt.equals(pass)){
            Intent goHome = new Intent(getApplicationContext(), Home.class);
            startActivity(goHome);
            finish();
        }else{
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage("wrong phone or password");
            dlgAlert.setTitle("Error Message...");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
