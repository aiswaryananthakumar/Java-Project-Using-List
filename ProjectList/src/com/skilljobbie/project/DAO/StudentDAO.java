package com.skilljobbie.project.DAO;

import com.skilljobbie.project.BO.StudentBO;
import java.util.List;

public interface StudentDAO {
	
	void insertStudent(StudentBO student);
    List<StudentBO> getAllStudents();
    void updateStudent(StudentBO student);
    StudentBO getStudentById(int id);
    List<StudentBO> searchStudentsByName(String name);
	boolean deleteStudent(int id);
    
	}
