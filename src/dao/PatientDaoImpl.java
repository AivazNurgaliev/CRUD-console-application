package dao;

import pojo.Patient;
import dbConnection.dbUtilConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements PatientDao{

    //Creating default Database
    public PatientDaoImpl() {
        try (Connection connection = dbUtilConnection.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT patients(name, age, hivInfected) VALUES ('Andrew', 41, false),"
                    + "('Mary', 34, true)");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to database!");
        }
    }

    @Override
    public List<Patient> getAll() {
        List<Patient> patientList = new ArrayList<>();
        try (Connection connetion = dbUtilConnection.getConnection()) {
            Statement statement = connetion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM patients");
            while(resultSet.next()) {
                Patient patient = new Patient();
                patient.setId(resultSet.getInt("id"));
                patient.setName(resultSet.getString("name"));
                patient.setAge(resultSet.getInt("age"));
                patient.setHivInfected(resultSet.getBoolean("hivInfected"));
                patientList.add(patient);
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to database!");
        } finally {
            return patientList;
        }
    }

    @Override
    public Patient get(int id) {
        Patient patient = new Patient();
        try (Connection connetion = dbUtilConnection.getConnection()) {
            PreparedStatement preparedStatement = connetion.prepareStatement("SELECT * FROM patients WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                patient.setId(id);
                patient.setName(resultSet.getString("name"));
                patient.setAge(resultSet.getInt("age"));
                patient.setHivInfected(resultSet.getBoolean("hivInfected"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to database!");
        } finally {
            return patient;
        }
    }

    @Override
    public void save(Patient patient) {
        try (Connection connetion = dbUtilConnection.getConnection()) {
            String sqlStr = "INSERT INTO patients(name, age, hivInfected) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connetion.prepareStatement(sqlStr);
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setInt(2, patient.getAge());
            preparedStatement.setBoolean(3, patient.isHivInfected());
            int rows = preparedStatement.executeUpdate();
            System.out.printf("\nAdded rows: %d\n", rows);
        } catch (SQLException e) {
            System.out.println("Failed to connect to database!");
        }
    }

    @Override
    public void update(Patient patient) {
        try (Connection connection = dbUtilConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE patients SET name = ?, age = ?, hivInfected = ? WHERE id = ?");
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setInt(2, patient.getAge());
            preparedStatement.setBoolean(3, patient.isHivInfected());
            preparedStatement.setInt(4, patient.getId());
            int rows = preparedStatement.executeUpdate();
            System.out.printf("\nUpdated rows: %d\n", rows);
        } catch (SQLException e) {
            System.out.println("Failed to connect to database!");
        }
    }

    @Override
    public void updateByName(String name, String desiredName) {
        try (Connection connection = dbUtilConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE patients SET name = ? WHERE name = ?");
            preparedStatement.setString(1, desiredName);
            preparedStatement.setString(2, name);
            int rows = preparedStatement.executeUpdate();
            System.out.printf("\nUpdated rows: %d\n", rows);
        } catch (SQLException e) {
            System.out.println("Failed to connect to database!");
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = dbUtilConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM patients WHERE id = ?");
            preparedStatement.setInt(1, id);
            int rows = preparedStatement.executeUpdate();
            System.out.printf("\nDeleted rows: %d\n", rows);
        } catch (SQLException e) {
            System.out.println("Failed to connect to database!");
        }
    }

    @Override
    public void deleteByName(String name) {
        try (Connection connection = dbUtilConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM patients WHERE name = ?");
            preparedStatement.setString(1, name);
            int rows = preparedStatement.executeUpdate();
            System.out.printf("\nDeleted rows: %d\n", rows);
        } catch (SQLException e) {
            System.out.println("Failed to connect to database!");
        }
    }
}
