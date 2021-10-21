package Intentcaller.Chandras.TI18D5.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import Intentcaller.Chandras.TI18D5.R;


public class MailFragment extends Fragment {

    TextView btSentWith;
    EditText etEmail, etSubject, etMessage;

    String email, subject, message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mail, container, false);

        btSentWith = (TextView)view.findViewById(R.id.txtSentWith);
        etEmail = (EditText)view.findViewById(R.id.edtPutEmail);
        etSubject = (EditText)view.findViewById(R.id.edtPutSubject);
        etMessage = (EditText)view.findViewById(R.id.edtPutMessage);

        btSentWith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = etEmail.getText().toString();
                subject = etSubject.getText().toString();
                message = etMessage.getText().toString();

                Intent sentWith = new Intent(Intent.ACTION_SEND);
                sentWith.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                sentWith.putExtra(Intent.EXTRA_SUBJECT, subject);
                sentWith.putExtra(Intent.EXTRA_TEXT, message);

                sentWith.setType("message/rfc822");

                startActivity(Intent.createChooser(sentWith, "Choose an Email Client"));
            }
        });

        return view;
    }
}