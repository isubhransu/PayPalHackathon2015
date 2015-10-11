package paypal.com.rsahack.paypalhackathon2015;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;

import com.example.subhransumishra.myapplication.backend.userApi.UserApi;
import com.example.subhransumishra.myapplication.backend.userApi.model.User;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Copyright 2015 Subhransu Mishra
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p/>
 * Purpose:
 *
 * @author Subhransu Mishra s.mishra@asu.edu
 *         MS Software Engineering, CIDSE, ASU
 * @version October 10 2015
 */
public class PaypalAsync extends AsyncTask<Void, Void, String> {
    private static UserApi service = null;
    User response = null;
    private Context context;
    String utxt;
    String ptxt;

    PaypalAsync(String utxt, String ptxt){
        this.utxt = utxt;
        this.ptxt = ptxt;
    }

    PaypalAsync(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        Log.d(this.getClass().getSimpleName(), "in LOGIN ondoInBackground on" +
                (Looper.myLooper() == Looper.getMainLooper() ? "Main thread" : "Async thread"));
        if (service == null) { // Only do this once
            UserApi.Builder builder = new UserApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://papypalhack.appspot.com/_ah/api/");
            service = builder.build();

            System.out.println("HERE IN AFTER API CONNECTS");
        }

        try {
            System.out.println("HERE IN TRY FOR SERVICE ACCESS");
            response = service.getUser(utxt).execute();
            System.out.println("Password is "+ response.getPassword());
            //System.out.println(response.getActive());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("HERE IN BEFORE RETURN SERVICE ACCESS");
        System.out.println(response.getPassword());
        return response.getPassword();
    }
}
class NewEventAsync extends AsyncTask<Void, Void, Void> {

    private static UserApi service = null;
    User response = null;
    private Context context;
    String name;
    String desc;

    NewEventAsync(String name, String desc){
        this.name = name;
        this.desc = desc;
    }

    @Override
    protected Void doInBackground(Void... params) {
        Log.d(this.getClass().getSimpleName(), "in LOGIN ondoInBackground on" +
                (Looper.myLooper() == Looper.getMainLooper() ? "Main thread" : "Async thread"));
        if (service == null) { // Only do this once
            UserApi.Builder builder = new UserApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://papypalhack.appspot.com/_ah/api/");
            service = builder.build();

            System.out.println("HERE IN AFTER API CONNECTS");
        }
        try {
            System.out.println("HERE IN TRY FOR SERVICE ACCESS");
            System.out.println(name +" AND "+desc);
            System.out.println(service.insertEvent(name, desc).execute());
            //System.out.println(response.getActive());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("HERE IN TRY FOR SERVICE ACCESS");
        //System.out.println(response.getActive());
        System.out.println("HERE IN BEFORE RETURN SERVICE ACCESS");
        return null;
    }
}

