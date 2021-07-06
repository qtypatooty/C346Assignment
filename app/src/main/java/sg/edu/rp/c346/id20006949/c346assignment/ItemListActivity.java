package sg.edu.rp.c346.id20006949.c346assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Collections;

public class ItemListActivity extends AppCompatActivity {
    EditText editnewproducts;
    EditText thefilter;
    EditText editproducts;
    EditText editnewdate;
    EditText editdate;
    Button add;
    Button clear;
    Button delete;
    Button update;
    ListView lvproducts;
    ArrayList<String> products;
    ArrayAdapter<String> adapter;
    Spinner spinner;
    Spinner spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        lvproducts = findViewById(R.id.lvproducts);
        products = new ArrayList<String>();
        editnewproducts=findViewById(R.id.editnewproducts);
        editproducts = findViewById(R.id.editproducts);
        editdate = findViewById(R.id.editdate);
        editnewdate = findViewById(R.id.editnewdate);
        thefilter= findViewById(R.id.thefilter);
        add= findViewById(R.id.add);
        clear = findViewById(R.id.clear);
        update= findViewById(R.id.update);
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        delete = findViewById(R.id.delete);
        products.add("Expires 2021-8-6 iPhone 12 Pro Max");
        products.add("Expires 2021-10-15 Dyson V10");
        products.add("Expires 2021-10-5 Dyson V11");
        products.add("Expires 2022-1-19 Samsung Galaxy S21");
        products.add("Expires 2021-10-4 Logitech G Pro X Superlight Wireless Gaming Mouse");
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,products);
        lvproducts.setAdapter(adapter);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (TextUtils.isEmpty(editproducts.getText().toString()) || (TextUtils.isEmpty(editdate.getText().toString()))){
                    Toast.makeText(ItemListActivity.this, "Empty field not allowed !",
                            Toast.LENGTH_SHORT).show();
                }else{
                String productName = editproducts.getText().toString();
                String date = editdate.getText().toString();
                products.add("Expires " + date + " " + productName);
                editproducts.setText("");
                editdate.setText("");
                Collections.sort(products, String.CASE_INSENSITIVE_ORDER);
                adapter.notifyDataSetChanged();
            }}
        });
        update.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editproducts.getText().toString()) || (TextUtils.isEmpty(editdate.getText().toString()))) {
                    Toast.makeText(ItemListActivity.this, "Empty field not allowed !",
                            Toast.LENGTH_SHORT).show();
                }else{
                String productName = editproducts.getText().toString();
                String date = editdate.getText().toString();
                String newdate = editnewdate.getText().toString();
                String newproduct = editnewproducts.getText().toString();
                int index = products.indexOf("Expires " + date + " " + productName);
                products.set(index, "Expires " + newdate + " " + newproduct);
                    editproducts.setText("");
                    editdate.setText("");
                    editnewdate.setText("");
                    editnewproducts.setText("");
                Collections.sort(products, String.CASE_INSENSITIVE_ORDER);
                adapter.notifyDataSetChanged();
                }}
        });
        clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                products.clear();
                adapter.notifyDataSetChanged();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (products.size() == 0) {
                    Toast.makeText(getApplicationContext(), "You don't have any product to remove", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    int index = Integer.parseInt(editproducts.getText().toString());
                    if (products.size() <= index) {
                        Toast.makeText(ItemListActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        products.remove(index);
                        adapter.notifyDataSetChanged();
                        editproducts.setText(null);
                        Collections.sort(products, String.CASE_INSENSITIVE_ORDER);
                    }
                }
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        editproducts.setHint(R.string.hintAdd);
                        editnewproducts.setEnabled(false);
                        editnewdate.setEnabled(false);
                        add.setEnabled(true);
                        delete.setEnabled(false);
                        update.setEnabled(false);
                        break;
                    case 1:
                        editproducts.setHint(R.string.hintUpdate);
                        editnewdate.setEnabled(true);
                        editnewproducts.setEnabled(true);
                        add.setEnabled(false);
                        update.setEnabled(true);
                        delete.setEnabled(false);
                        break;
                    case 2:
                        editproducts.setHint(R.string.hintDel);
                        editnewproducts.setEnabled(false);
                        editnewdate.setEnabled(false);
                        add.setEnabled(false);
                        update.setEnabled(false);
                        delete.setEnabled(true);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Calendar month = Calendar.getInstance();
                switch (position) {
                    case 0:
                        month.add(Calendar.MONTH, 1);
                        SimpleDateFormat onemonth = new SimpleDateFormat("yyyy-m");
                        String onexpire = onemonth.format(month.getTime());
                        (ItemListActivity.this).adapter.getFilter().filter(onexpire);
                        thefilter.setText(getString(R.string.thefilter) + "1 month");
                        adapter.notifyDataSetChanged();
                        thefilter.setText(getString(R.string.thefilter));
                        break;
                    case 1:
                        month.add(Calendar.MONTH, 3);
                        SimpleDateFormat threemonth = new SimpleDateFormat("yyyy-m");
                        String threexpire = threemonth.format(month.getTime());
                        (ItemListActivity.this).adapter.getFilter().filter(threexpire);
                        thefilter.setText(getString(R.string.thefilter) + "3 months");
                        adapter.notifyDataSetChanged();
                        thefilter.setText(getString(R.string.thefilter));
                        break;
                    case 2:
                        month.add(Calendar.MONTH, 6);
                        SimpleDateFormat sixmonth = new SimpleDateFormat("yyyy-m");
                        String sixexpire = sixmonth.format(month.getTime());
                        (ItemListActivity.this).adapter.getFilter().filter(sixexpire);
                        thefilter.setText(getString(R.string.thefilter) + "6 months");
                        adapter.notifyDataSetChanged();
                        thefilter.setText(getString(R.string.thefilter));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}