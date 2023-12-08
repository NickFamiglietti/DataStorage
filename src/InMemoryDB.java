interface InMemoryDB {
    Integer get(String key);

    void put(String key, int val) throws Exception;

    void begin_transaction();

    void commit() throws Exception;

    void rollback() throws Exception;
}

