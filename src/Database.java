import java.util.HashMap;

public class Database implements InMemoryDB{
    HashMap <String, Integer> db = new HashMap<>();
    HashMap <String, Integer> transactionDb = new HashMap<>();
    boolean activeTransaction = false;
    @Override
    public Integer get(String key) {
        return db.get(key);
    }

    @Override
    public void put(String key, int val) throws Exception {
        if(!activeTransaction){
            throw new Exception("No Active Transaction");
        }
        transactionDb.put(key, val);
    }

    @Override
    public void begin_transaction(){
        if(activeTransaction){
            System.out.println ("Only One Transaction Allowed");
            return;
        }
        activeTransaction = true;
    }

    @Override
    public void commit() throws Exception {
        if(!activeTransaction){
            throw new Exception("No Active Transaction");
        }
        // Move all transaction items into actual db
        db.putAll(transactionDb);
        activeTransaction=false;
        transactionDb.clear();
    }

    @Override
    public void rollback() throws Exception {
        if(!activeTransaction){
            throw new Exception("No Active Transaction");
        }
        activeTransaction=false;
        transactionDb.clear();
    }
}