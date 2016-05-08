package com.journaldev.expandablelistview;

/*
 * Created by macbookpro on 5/5/16.
 */

        import android.app.DatePickerDialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.Snackbar;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.View;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Calendar;

public class AddItem extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);

        spinner = (Spinner)findViewById(R.id.spinner);

        adapter = ArrayAdapter.createFromResource(this,R.array.item_category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + "selected ", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void buttonDate(View view)
    {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        DatePickerDialog datePicker = new DatePickerDialog(this, this, year, month, day);
        datePicker.show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_item, menu);
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day)
    {
        String date;
        String month2;
        month = month +1;
        //SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        //String formattedDate = df.parse(year+""+month+""+day);
        if (day <10)
        {
            date = "0"+day;
        }
        else
        {
            date =""+day;
        }
        if (month <10)
        {
            month2 = "0"+month;
        }
        else
        {
            month2 =""+month;
        }
        EditText setDate = (EditText)findViewById(R.id.EditDate);
        setDate.setText(year +month2+date);

    }

    public void buttonSubmit (View view)
    {
        Intent intent2 = new Intent (this, MainActivity.class);

        EditText ItemName = (EditText)findViewById(R.id.EditItem);
        String contents = ItemName.getText().toString();

        Spinner category = (Spinner)findViewById(R.id.spinner);
        String contents2 = category.getSelectedItem().toString();

        EditText ExpDate = (EditText) findViewById(R.id.EditDate);
        String contents3 = ExpDate.getText().toString();


        EditText Quantity = (EditText)findViewById(R.id.EditQuantity);
        String contents4 = Quantity.getText().toString();

        if (contents.equals("") || /*contents2.equals("") ||*/ contents3.equals("") || contents4.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Fill in all fields.", Toast.LENGTH_SHORT).show();
        }

        else
        {
            int e = 1;
            intent2.putExtra("ItemName",contents);
            intent2.putExtra("Category",contents2);
            intent2.putExtra("ExpDate",Integer.parseInt(contents3));
            intent2.putExtra("Quantity",Integer.parseInt(contents4));
            intent2.putExtra("Add",e);
            //startActivity(intent2);

            setResult(1,intent2);
            finish();
        }

    }

    public void Clear(View view) {

        EditText ItemName = (EditText)findViewById(R.id.EditItem);
        ItemName.setText("");

        Spinner category = (Spinner)findViewById(R.id.spinner);
        category.setSelection(0);

        EditText ExpDate = (EditText)findViewById(R.id.EditDate);
        ExpDate.setText("");

        EditText Quantity = (EditText)findViewById(R.id.EditQuantity);
        Quantity.setText("");
    }

}
