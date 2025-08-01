package com.skilljobbie.project.Service;

import com.skilljobbie.project.BO.StudentBO;
import java.util.List;

public interface StudentService {
	    void addStudent(StudentBO student);
	    List<StudentBO> getAllStudents();
	    void updateStudent(StudentBO student);
	    StudentBO getStudentById(int id);
	    List<StudentBO> searchStudentsByName(String name);
		boolean deleteStudent(int id);
	    
}
