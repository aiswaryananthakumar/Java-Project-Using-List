package com.skilljobbie.project.DAO;

import com.skilljobbie.project.BO.StudentBO;
import DataBaseConnection.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    private Connection conn;

    public StudentDAOImpl() {
        conn = DBConnection.getConnection();
    }

    @Override
    public void insertStudent(StudentBO student) {
        String query = "INSERT INTO Student (id, name, age, email, phone, course, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, student.getId());  
            ps.setString(2, student.getName());
            ps.setInt(3, student.getAge());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getPhone());
            ps.setString(6, student.getCourse());
            ps.setString(7, student.getAddress());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        student.setId(generatedKeys.getInt(1)); 
                    }
                }
            }
            System.out.println("Student inserted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<StudentBO> getAllStudents() {
        List<StudentBO> students = new ArrayList<>();
        String query = "SELECT id, name, age, email, phone, course, address FROM Student";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                StudentBO student = new StudentBO();
                student.setId(rs.getInt("id")); 
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setCourse(rs.getString("course"));
                student.setAddress(rs.getString("address"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void updateStudent(StudentBO student) {
        String query = "UPDATE Student SET name = ?, age = ?, email = ?, phone = ?, course = ?, address = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
           
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getPhone());
            ps.setString(5, student.getCourse());
            ps.setString(6, student.getAddress());
            ps.setInt(7, student.getId());  

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student updated successfully with ID: " + student.getId());
            } else {
                System.out.println("Student update failed. ID not found: " + student.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public StudentBO getStudentById(int id) {
        String query = "SELECT name, age, email, phone, course, address FROM Student WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                StudentBO student = new StudentBO();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setCourse(rs.getString("course"));
                student.setAddress(rs.getString("address"));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StudentBO> searchStudentsByName(String name) {
        List<StudentBO> students = new ArrayList<>();
        String query = "SELECT id, name, age, email, phone, course, address FROM Student WHERE name LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, "%" + name + "%"); 
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StudentBO student = new StudentBO();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setCourse(rs.getString("course"));
                student.setAddress(rs.getString("address"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public boolean deleteStudent(int id) {
        String query = "DELETE FROM Student WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student with ID " + id + " deleted successfully.");
                return true;
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
