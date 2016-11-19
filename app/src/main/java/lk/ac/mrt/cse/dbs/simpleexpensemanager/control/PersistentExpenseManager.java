package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

import android.database.sqlite.SQLiteDatabase;


/**
 * Created by manujith on 11/15/16.
 */
public class PersistentExpenseManager extends ExpenseManager {
    @Override
    public void setup(){
        SQLiteDatabase mydatabase = SQLiteDatabase.openOrCreateDatabase("expenses",null);

        

    }
}
