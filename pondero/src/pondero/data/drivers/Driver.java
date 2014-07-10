package pondero.data.drivers;

import pondero.data.model.PModel;

public abstract class Driver {

    private String connectionString;

    public Driver(final String connectionString) {
        this.connectionString = connectionString;
    }

    public String getConnectionString() {
        return connectionString;
    }

    protected void setConnectionString(final String connectionString) {
        this.connectionString = connectionString;
    }

    public abstract void open() throws Exception;

    public abstract PModel fetchModel() throws Exception;

    public abstract void pushModel(PModel model) throws Exception;

    public abstract void close() throws Exception;

}
