package com.example.enterprise.intenttosendanemail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;

    private RelativeLayout mRelativeLayout;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the application context
        mContext = getApplicationContext();

        // Get the activity
        mActivity = MainActivity.this;

        // Get the widgets reference from XML layout
        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl);
        mButton = (Button) findViewById(R.id.btn);

        // Set a click listener for the button
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Specify the email related variables values
                String[] emails = new String[]{
                        "someone@example.com",
                        "test@example.com",
                        "jones@example.com",
                        "jenny@example.com"
                };

                // emails for cc
                String[] emailsCC = new String[]{
                        "jim@example.com"
                };

                // emails for bcc
                String[] emailsBCC = new String[]{
                        "tom@example.com"
                };

                String mailSubject = "This is email subject";
                String mailBody = "This is the body of the email";

                /*
                    Intent
                        An intent is an abstract description of an operation to be performed. It can
                        be used with startActivity to launch an Activity, broadcastIntent to send it
                        to any interested BroadcastReceiver components, and startService(Intent)
                        or bindService(Intent, ServiceConnection, int) to communicate with
                        a background Service.

                        An Intent provides a facility for performing late runtime binding between
                        the code in different applications. Its most significant use is in the
                        launching of activities, where it can be thought of as the glue between
                        activities. It is basically a passive data structure holding an abstract
                        description of an action to be performed.
                */
                /*
                    public static final String ACTION_SENDTO
                        Activity Action: Send a message to someone specified by the data.

                        Input : getData() is URI describing the target.
                        Output : nothing.
                        Constant Value : "android.intent.action.SENDTO"
                */
                // Initialize a new Intent
                Intent intent = new Intent(Intent.ACTION_SENDTO);

                /*
                    public static final String ACTION_SEND
                        Activity Action: Deliver some data to someone else. Who the data is being
                        delivered to is not specified; it is up to the receiver of this action to
                        ask the user where the data should be sent.

                        When launching a SEND intent, you should usually wrap it in a chooser
                        (through createChooser(Intent, CharSequence)), which will give the proper
                        interface for the user to pick how to send your data and allow you to
                        specify a prompt indicating what they are doing.

                        Input: getType() is the MIME type of the data being sent. get*Extra can have
                        either a EXTRA_TEXT or EXTRA_STREAM field, containing the data to be sent.
                        If using EXTRA_TEXT, the MIME type should be "text/plain".
                */

                /*
                    Action
                        ACTION_SENDTO (for no attachment) or
                        ACTION_SEND (for one attachment) or
                        ACTION_SEND_MULTIPLE (for multiple attachments)
                */
                // Alternate way to send email
                //Intent intent = new Intent(Intent.ACTION_SEND);

                /*
                    public Intent setType (String type)

                        Set an explicit MIME data type.
                        This is used to create intents that only specify a type and not data,
                        for example to indicate the type of data to return.

                        This method automatically clears any data that was previously set
                        (for example by setData(Uri)).

                        Note: MIME type matching in the Android framework is case-sensitive, unlike
                        formal RFC MIME types. As a result, you should always write your MIME types
                        with lower case letters, or use normalizeMimeType(String) or
                        setTypeAndNormalize(String) to ensure that it is converted to lower case.                */
                // Set the intent type
                //intent.setType("text/plain");

                // Alternate Intent type
                //intent.setType("message/rfc822");
                //intent.setType("*/*");

                // Add extended data to the Intent
                /*
                    public static final String EXTRA_EMAIL
                        A String[] holding e-mail addresses that should be delivered to.

                        Constant Value: "android.intent.extra.EMAIL"
                */
                intent.putExtra(Intent.EXTRA_EMAIL,emails);

                /*
                    public static final String EXTRA_CC
                        A String[] holding e-mail addresses that should be carbon copied.

                        Constant Value: "android.intent.extra.CC"
                */
                /*
                    public static final String EXTRA_BCC
                        A String[] holding e-mail addresses that should be blind carbon copied.

                        Constant Value: "android.intent.extra.BCC"
                */
                // Define the email cc and bcc
                intent.putExtra(Intent.EXTRA_CC,emailsCC);
                intent.putExtra(Intent.EXTRA_BCC,emailsBCC);

                /*
                    public static final String EXTRA_SUBJECT
                        A constant string holding the desired subject line of a message.

                        Constant Value: "android.intent.extra.SUBJECT"
                */
                intent.putExtra(Intent.EXTRA_SUBJECT,mailSubject);

                /*
                    public static final String EXTRA_TEXT
                        A constant CharSequence that is associated with the Intent, used with
                        ACTION_SEND to supply the literal data to be sent. Note that this may be a
                        styled CharSequence, so you must use Bundle.getCharSequence() to retrieve it.

                        Constant Value: "android.intent.extra.TEXT"
                */
                intent.putExtra(Intent.EXTRA_TEXT,mailBody);

                /*
                    public Intent setData (Uri data)
                        Set the data this intent is operating on. This method automatically clears
                        any type that was previously set by setType(String) or setTypeAndNormalize(String).

                        Note: scheme matching in the Android framework is case-sensitive, unlike the
                        formal RFC. As a result, you should always write your Uri with a lower case
                        scheme, or use normalizeScheme() or setDataAndNormalize(Uri) to ensure that
                        the scheme is converted to lower case.

                    Parameters
                        data : Uri: The Uri of the data this intent is now targeting.
                    Returns
                        Intent : Returns the same Intent object, for chaining multiple calls into
                                 a single statement.
                */
                // For only email app should handle this intent
                intent.setData(Uri.parse("mailto:"));

                // Try to start the activity
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }else{
                    // If there are no email client installed in this device
                    Toast.makeText(mContext,"No email client installed in this device.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
