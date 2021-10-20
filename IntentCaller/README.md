## Intent Caller 1.0 
Tugas PAS, STT Pelita Bangsa Cikarang, Chandra, TI18D5, dengan mengimplementasikan Intent Caller fragment Maps, Phone, Webview & Send Email, Berikut fitur yang sudah diterapkan di project ini

## Dosen Pengampu
Bpk. Donny

## Map Traker dengan API Maps Google

Dengan implementasi Maps pada Fragment kita bisa menerapkannya menggunakan meta-data pada bagian AndroidManifest.xml

```.xml
<meta-data
   android:name="com.google.android.geo.API_KEY"
   ndroid:value="API_KEY"/>
```

<img src="https://github.com/eljitech/intentcaller/blob/master/Pesanan%20Mahasiswa-20210428/Pesanan%20Mahasiswa/Chandra/maps-tracker.gif" height="600"/>

## Web Loader dengan Webview Android

Untuk implementasi Webview kita dapat menambahkan pada Layout .xml 

```.xml
<WebView
      android:visibility="gone"
      android:id="@+id/wvWeb"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      android:background="#F3EBEB"/>
```

<img src="https://github.com/eljitech/intentcaller/blob/master/Pesanan%20Mahasiswa-20210428/Pesanan%20Mahasiswa/Chandra/webview.gif" height="600"/>

## Phone Call

Untuk implementasi Phone Call kita dapat menggunakan 2 sourcecode berikut

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

dan 

```.java
Intent intent = new Intent(Intent.ACTION_DIAL);
intent.setData(Uri.parse("tel:+62" + etPhoneNumber.getText().toString()));
startActivity(intent);
```

<img src="https://github.com/eljitech/intentcaller/blob/master/Pesanan%20Mahasiswa-20210428/Pesanan%20Mahasiswa/Chandra/phone.gif" height="600"/>

## Send Mail with Intent to Mail Client

Untuk implementasi akses Mail Client kita dapat menggunakan sourcecode dibawah ini

```.java
Intent sentWith = new Intent(Intent.ACTION_SEND);
sentWith.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
sentWith.putExtra(Intent.EXTRA_SUBJECT, subject);
sentWith.putExtra(Intent.EXTRA_TEXT, message);

sentWith.setType("message/rfc822");

startActivity(Intent.createChooser(sentWith, "Choose an Email Client"));
```

<img src="https://github.com/eljitech/intentcaller/blob/master/Pesanan%20Mahasiswa-20210428/Pesanan%20Mahasiswa/Chandra/mail.gif" height="600"/>

## Testing App
Untuk mencoba aplikasi bisa di download file .apk nya <a href="https://drive.google.com/file/d/1Yc6sHVBMMkcbE3m8DnNPJOUo7lxZdXX6/view?usp=sharing">download disini</a>
