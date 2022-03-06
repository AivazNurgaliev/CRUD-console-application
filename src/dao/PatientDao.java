package dao;

import java.util.List;

import pojo.Patient;


public interface PatientDao {

    public List<Patient> getAll();
    public Patient get(int id);
    public void save(Patient patient);
    public void update(Patient patient);
    public void updateByName(String name, String desiredName);
    public void delete(int id);
    public void deleteByName(String name);
}
