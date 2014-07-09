package pondero.model.drivers;

import pondero.model.foundation.PModel;

public abstract class Driver {

    private String connectionString;

    public Driver(final String connectionString) {
        this.connectionString = connectionString;
    }

    public abstract void close() throws Exception;

    public String getConnectionString() {
        return connectionString;
    }

    public abstract PModel fetchModel() throws Exception;

    public abstract void open() throws Exception;

    public abstract void commitModel(PModel model) throws Exception;

    protected void setConnectionString(final String connectionString) {
        this.connectionString = connectionString;
    }

}
