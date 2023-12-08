//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        InMemoryDB inmemoryDB = new Database();

        // should return null, because A doesn’t exist in the DB yet
        System.out.println(inmemoryDB.get("A"));

        // should throw an error because a transaction is not in progress
        try{
            inmemoryDB.put("A", 5);
        }
        catch(Exception e){
            System.out.println("Success");
        }
        // starts a new transaction
        inmemoryDB.begin_transaction();

        // set’s value of A to 5, but its not committed yet
        inmemoryDB.put("A", 5);

        // should return null, because updates to A are not committed yet
        System.out.println(inmemoryDB.get("A"));

        // update A’s value to 6 within the transaction
        inmemoryDB.put("A", 6);

        // commits the open transaction
        inmemoryDB.commit();
        // should return 6, that was the last value of A to be committed
        System.out.println(inmemoryDB.get("A"));

        // throws an error, because there is no open transaction
        try{
            inmemoryDB.commit();
        }
        catch(Exception e){
            System.out.println("Success");
        }
        // throws an error because there is no ongoing transaction
        try{
            inmemoryDB.rollback();
        }
        catch(Exception e){
            System.out.println("Success");
        }
        // should return null because B does not exist in the database
        System.out.println(inmemoryDB.get("B"));

        // starts a new transaction
        inmemoryDB.begin_transaction();

        // Set key B’s value to 10 within the transaction
        inmemoryDB.put("B", 10);

        // Rollback the transaction - revert any changes made to B
        inmemoryDB.rollback();

        // Should return null because changes to B were rolled back
        System.out.println(inmemoryDB.get("B"));
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
    }
}