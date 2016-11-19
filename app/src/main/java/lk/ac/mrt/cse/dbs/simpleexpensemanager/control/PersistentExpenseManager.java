package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

import android.database.sqlite.SQLiteDatabase;


/**
 * Created by manujith on 11/15/16.
 */
public class PersistentExpenseManager extends ExpenseManager {
    @Override
    public void setup(){
        SQLiteDatabase mydatabase = SQLiteDatabase.openOrCreateDatabase("expenses",null);

        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Account(" +
                "Account_no VARCHAR PRIMARY KEY," +
                "Bank VARCHAR," +
                "Holder VARCHAR," +
                "Initial_amt REAL" +
                " );");

        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Transactions(" +
                "Transaction_id INTEGER PRIMARY KEY," +
                "Account_no VARCHAR" +
                "Type INT2," +
                "Amt REAL," +
                "Date DATE," +
                "FOREIGN KEY (Account_no) REFERENCES Account(Account_no)" +
                ");");

    }
}
