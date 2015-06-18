package com.ringcentral.androidsdk;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class MainActivity extends Activity implements View.OnClickListener, Version.VersionResponse {

    Button button1, button2;

    public static void log(String message) {
        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh;

        try {

            // This block configure the logger with handler and formatter
            fh = new FileHandler("/Users/andrew.pang/log.txt");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
            logger.info(message);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("Yoto");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);

        //myVersion = new Version(this);
        //myVersion.execute();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button1:

                TextView TextView1 = (TextView) findViewById(R.id.textView1);
                String grantType = "password";
                String username = "15856234166";
                String password = "P@ssw0rd";
                String key = "xhK3uzISTEaEYhFAtadVug";
                String secret = "1YRoPu64TeCOe_ZJy3ggLwGg-QDQd6QaWpSyIT8AxmjA";

                OAuth o = new OAuth();
                String access_token = "";
                try {
                    o.OAuthorizer(grantType, username, password, key, secret);
                    TextView1.setText("Pressed log in");
                } catch (Exception e) {
                    e.printStackTrace();
                    access_token = "Error";
                    TextView1.setText(access_token);
                }
                //TextView TextView1 = (TextView) findViewById(R.id.textView1);
                //TextView1.setText(access_token);

                break;

            case R.id.button2:
                Version myVersion = new Version();
                myVersion.delegate = this;
                myVersion.execute();
                break;

        }
    }

    public void versionProcessFinish(String output) {
        Log.d("Hey", output);
        TextView TextView1 = (TextView) findViewById(R.id.textView1);
        TextView1.setText(output);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
