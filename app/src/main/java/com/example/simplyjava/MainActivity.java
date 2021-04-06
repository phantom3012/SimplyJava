package com.example.simplyjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int quantity;
    TextView orderobj;
    CheckBox creme,chocolate;
    EditText name,viewqobj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewqobj=(EditText)findViewById(R.id.viewq);
        creme=(CheckBox)findViewById(R.id.checkBox1);
        chocolate=(CheckBox)findViewById(R.id.checkBox2);
        name=(EditText)findViewById(R.id.namebox);
        orderobj=(TextView)findViewById(R.id.finalorder);
    }

    public void addQ(View view) {
        quantity=Integer.parseInt(viewqobj.getText().toString());
        quantity++;
        if(quantity<0){
            quantity=0;
        }
        viewqobj.setText(Integer.toString(quantity));
    }

    public void subQ(View view) {
        quantity=Integer.parseInt(viewqobj.getText().toString());
        quantity--;
        if(quantity<0){
            quantity=0;
        }
        viewqobj.setText(Integer.toString(quantity));
    }

    public void resetorder(View view) {
        quantity=0;
        name.getText().clear();
        creme.setChecked(false);
        chocolate.setChecked(false);
        viewqobj.setText(Integer.toString(quantity));
        orderobj.setText(null);
    }

    public void emailorder(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"f20200435@pilani.bits-pilani.ac.in"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Order Receipt");
        i.putExtra(Intent.EXTRA_TEXT   , orderobj.getText().toString());
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public void placeorder(View view) {
        float totalCost=20;
        if(creme.isChecked()==true){
            totalCost+=5;
        }
        if(chocolate.isChecked()==true){
            totalCost+=10;
        }
        totalCost*=Integer.parseInt(viewqobj.getText().toString());
        orderobj.setText("Hello "+name.getText().toString()+"\n"+"The final order summary: \nBase Price: ₹20\n");
        if(creme.isChecked()==true){
            orderobj.append("creme price: ₹5\n");
        }
        if(chocolate.isChecked()==true){
            orderobj.append("chocolate price: ₹10\n");
        }
        orderobj.append("Quantity: "+viewqobj.getText().toString()+"\n"+"Final cost is now: "+totalCost);

    }
}