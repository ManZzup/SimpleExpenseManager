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
        this.ctx = ctx;
        setup();
    }
    @Override
    public void setup(){
        SQLiteDatabase mydatabase = ctx.openOrCreateDatabase("expenses5", ctx.MODE_PRIVATE, null);

        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Account(" +
                "Account_no VARCHAR PRIMARY KEY," +
                "Bank VARCHAR," +
                "Holder VARCHAR," +
                "Initial_amt REAL" +
                " );");

        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS TransactionLog(" +
                "Transaction_id INTEGER PRIMARY KEY," +
                "Account_no VARCHAR," +
                "Type INT," +
                "Amt REAL," +
                "Log_date DATE," +
                "FOREIGN KEY (Account_no) REFERENCES Account(Account_no)" +
                ");");

        AccountDAO accountDAO = new PersistentAccountDAO(mydatabase);

        setAccountsDAO(accountDAO);

        setTransactionsDAO(new PersistentTransactionDAO(mydatabase));
    }
}
