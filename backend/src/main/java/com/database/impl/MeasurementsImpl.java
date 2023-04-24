package com.database.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.entities.Measurements;

public class MeasurementsImpl implements Entity<Measurements> {

    private int ID;
    private String table = "measurements";
    private Measurements measurement;

    public MeasurementsImpl(Measurements measurement) {
        this.measurement = measurement;
    }

    public MeasurementsImpl(int ID) {
        this.ID = ID;
    }

    @Override
    public String getTable() {
        return this.table;
    }

    @Override
    public String createEntityRecordString() {
        String sql = "INSERT INTO " + this.table + " (measurement) "
                   + "VALUES " + this.measurement.getValues() + ";";
        return sql;
    }

    @Override
    public String deleteEntityRecordString() {
        String sql = "DELETE FROM " + this.table + " WHERE id ='" + this.measurement.getID() + "';";
        return sql;
    }

    @Override
    public String updateEntityRecordString(String column, String value, String conditionColumn, String conditionValue) {
        String sql = "UPDATE " + this.table + " SET " + column + " = '" + value 
                   + "' WHERE " + conditionColumn + "=" + conditionValue + ";";
        return sql;
    }

    @Override
    public String getEntityRecordString() {
        String sql = "SELECT * FROM " + this.table + " WHERE id = '" + this.ID+"';";
        return sql;
    }

    @Override
    public String getAllEntityRecordsString() {
        String sql = "SELECT * FROM " + this.table + ";";
        return sql;
    }

    @Override
    public void createEntityRecordObject(ResultSet resultSet) throws SQLException {
        String measurement = null;

        while(resultSet.next()) {
            this.ID = resultSet.getInt("id");
            measurement = resultSet.getString("measurement");
        }
        this.measurement = new Measurements(this.ID, measurement);
    }

    @Override
    public <T> T getEntityRecordObject() {
        return (T) this.measurement;
    }
    
}
