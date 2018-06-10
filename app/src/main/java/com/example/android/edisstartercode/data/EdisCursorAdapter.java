package com.example.android.edisstartercode.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.edisstartercode.R;

import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_GUARDIAN;
import static com.example.android.edisstartercode.data.EdisContract.EdisEntry.COLUMN_STUDENT_NAME;

/**
 * Created by Delight on 27/04/2018.
 */

public class EdisCursorAdapter extends CursorAdapter {

    public EdisCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // The bindView method is used to bind all data to a given view
        // such as setting the text on a TextView

        /* Find fields to populate in inflated template*/
        TextView nameTextView = view.findViewById(R.id.name);
        TextView summaryTextView = view.findViewById(R.id.level);

        // Find the columns of students attributes we are interested in
        int nameColumnIndex = cursor.getColumnIndex(COLUMN_STUDENT_NAME);
        int guardianColumnIndex = cursor.getColumnIndex(COLUMN_STUDENT_GUARDIAN);

        // Extract pet attributes from the cursor for the current student
        String studentName = cursor.getString(nameColumnIndex);
        String studentGuardian = cursor.getString(guardianColumnIndex);

        // Populate fields with extracted properties for the current pet
        nameTextView.setText(studentName);
        summaryTextView.setText(studentGuardian);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
    }
}
