package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentTransactionDAO;


/**
 * Created by manujith on 11/15/16.
 */
public class PersistentExpenseManager extends ExpenseManager {
    private Context ctx;
    public PersistentExpenseManager(Context ctx){
        //Point the constructor to the setup function or our expense manager doesnt
        //get initialized
        this.ctx = ctx;
        setup();
    }
    @Override
    public void setup(){
        //First open an existing database or create new one
        SQLiteDatabase mydatabase = ctx.openOrCreateDatabase("expenses5", ctx.MODE_PRIVATE, null);

        //If it's the first time, we have to create the databases.
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Account(" +
                "Account_no VARCHAR PRIMARY KEY," +
                "Bank VARCHAR," +
                "Holder VARCHAR," +
                "Initial_amt REAL" +
                " );");

        //DONOT create a database called Transaction
        //It is a reserved keyword and will give errors in queries
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS TransactionLog(" +
                "Transaction_id INTEGER PRIMARY KEY," +
                "Account_no VARCHAR," +
                "Type INT," +
                "Amt REAL," +
                "Log_date DATE," +
                "FOREIGN KEY (Account_no) REFERENCES Account(Account_no)" +
                ");");



        //These two functions will hold our DAO instances in memory till the program exists
        setAccountsDAO(new PersistentAccountDAO(mydatabase));
        setTransactionsDAO(new PersistentTransactionDAO(mydatabase));
    }
}
