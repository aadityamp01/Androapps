## Intent Caller v1.0 - Hacktoberfest 2021
Hi! We made this application to contribute to the @aadityamp01 account on GitHub at Hacktoberfest 2021, here are the features I added in this application.

## Fitur 1 : Map Traker dengan API Maps Google

With Maps implementation on Fragment we can implement it using meta-data in `AndroidManifest.xml`

```.xml
<meta-data
   android:name="com.google.android.geo.API_KEY"
   ndroid:value="API_KEY"/>
```

<img src="https://github.com/guzzlecode/Androapps/blob/asep-septiadi/IntentCaller/capture/maps-tracker.gif" height="600"/>

## Fitur 2 : Web Loader with Webview Android (Simple Web Browser)

For implementation of Webview we can add in Layout .xml

```.xml
<WebView
      android:visibility="gone"
      android:id="@+id/wvWeb"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      android:background="#F3EBEB"/>
```

<img src="https://github.com/guzzlecode/Androapps/blob/asep-septiadi/IntentCaller/capture/web-browser-simple.gif" height="600"/>

## Fitur 3 : Phone Call

For Phone Call implementation, we can use the following 2 source codes

```.java
final int REQUEST_PHONE_CALL = 1;
Intent callIntent = new Intent(Intent.ACTION_CALL);
callIntent.setData(Uri.parse("tel:+62" + etPhoneNumber.getText().toString()));
if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
   if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
       ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
   } else {
       startActivity(callIntent);
   }
}
```

And

```.java
Intent intent = new Intent(Intent.ACTION_DIAL);
intent.setData(Uri.parse("tel:+62" + etPhoneNumber.getText().toString()));
startActivity(intent);
```

<img src="https://github.com/guzzlecode/Androapps/blob/asep-septiadi/IntentCaller/capture/caller-intent.gif" height="600"/>

##  Fitur 4 : Send Mail with Intent to Mail Client

For the implementation of Mail Client access, we can use the source code below:

```.java
Intent sentWith = new Intent(Intent.ACTION_SEND);
sentWith.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
sentWith.putExtra(Intent.EXTRA_SUBJECT, subject);
sentWith.putExtra(Intent.EXTRA_TEXT, message);

sentWith.setType("message/rfc822");

startActivity(Intent.createChooser(sentWith, "Choose an Email Client"));
```

<img src="https://github.com/guzzlecode/Androapps/blob/asep-septiadi/IntentCaller/capture/email-intent.gif" height="600"/>

## Testing App
To try the application, you can download the .apk file <a href="https://drive.google.com/file/d/1v50fJ7D2ho-MzX4CtRACPVKiAEnfDI6p/view?usp=sharing">Download Here</a>
