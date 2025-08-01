package com.skilljobbie.project.Service;

import com.skilljobbie.project.BO.StudentBO;
import com.skilljobbie.project.DAO.StudentDAO;
import com.skilljobbie.project.DAO.StudentDAOImpl;
import java.util.List;

public class StudentServiceImpl implements StudentService {
	
	 private StudentDAO studentDAO;

	    public StudentServiceImpl() {
	        this.studentDAO = new StudentDAOImpl();
	    }

	    @Override
	    public void addStudent(StudentBO student) {
	        studentDAO.insertStudent(student);
	    }

	    @Override
	    public List<StudentBO> getAllStudents() {
	        return studentDAO.getAllStudents();
	    }

	    @Override
	    public void updateStudent(StudentBO student) {
	        studentDAO.updateStudent(student);
	    }

	    @Override
	    public StudentBO getStudentById(int id) {
	        return studentDAO.getStudentById(id);
	    }

	    @Override
	    public List<StudentBO> searchStudentsByName(String name) {
	        return studentDAO.searchStudentsByName(name);
	    }
	    
	    @Override
	    public boolean deleteStudent(int id) {
	        return studentDAO.deleteStudent(id);
	    }
	
}
