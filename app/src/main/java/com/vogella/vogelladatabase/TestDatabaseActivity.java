//===============================================
//Title: VogellaDatabase
//Author: Trish Valeri
//Contributors: Vogella.com and CIS 3334
//Dates: 4/1/16 - 4/3/16
//Purpose: Add items to database and saves it, displays it in a list.
//         (become familiar with SQLiteDatabase)
//===============================================

package com.vogella.vogelladatabase;

import java.util.List;

import android.app.ListActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

public class TestDatabaseActivity extends ListActivity {

    private CommentsDataSource datasource;      // our link to the datasource for SQLite access
    private int listPosition = 0;                // currently selected item in the list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_database);

        datasource = new CommentsDataSource(this);
        datasource.open();

        List<Comment> values = datasource.getAllComments();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

        // need to grab the list item click so we remember what item is selected
        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                listPosition = position;
                //Toast.makeText(SuggestionActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Will be called via the onClick attribute
    // of the buttons in activity_test_database.xml
    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
        Comment comment = null;
        switch (view.getId()) {
            case R.id.add:
                // Create variables for UI components
                EditText txtComment = (EditText) findViewById(R.id.etComment);
                EditText txtRating = (EditText) findViewById(R.id.etRating);

                String strRating = txtRating.getText().toString();      //string to store rating
                int intRating = Integer.parseInt(strRating);        //int to parse then store rating
                comment = datasource.createComment(txtComment.getText().toString(),
                        txtRating.getText().toString());
                //make sure rating input is between 1 and 5, otherwise give an error message to
                // the user.
                if ((intRating >= 1) && (intRating <= 5)) {
                    adapter.add(comment);
                    txtComment.setText("");
                    txtRating.setText("");
                }
                else {
                    txtRating.setError("Input must be a number between 1 and 5");
                }
                break;
            case R.id.delete:
                // The selected item position is stored in the variable listPosition
                // by the onItemClick listener
                if (getListAdapter().getCount() > listPosition) {
                    comment = (Comment) getListAdapter().getItem(listPosition);
                    datasource.deleteComment(comment);
                    adapter.remove(comment);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}
