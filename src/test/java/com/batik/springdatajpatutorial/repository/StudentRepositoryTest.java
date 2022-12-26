package com.batik.springdatajpatutorial.repository;

import com.batik.springdatajpatutorial.entity.Guardian;
import com.batik.springdatajpatutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    // for insert data
//    @Test
//    private void saveStudent(){
//        Student student = Student.builder().emailId("wayne@gmail.com")
//                .firstName("Suvodeep").lastName("Biswas")
////                .guardianName("Siddyut")
////                .guardianEmail("Siddyut@gmial.com")
////                .guardianMobile("9945454997")
//                .build();
//
//        studentRepository.save(student);
//    }
    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .name("Siddyut")
              .email("Siddyut@gmial.com")
                .mobile("9945454997").build();

        Student student = Student.builder()
                .emailId("wayne@gmail.com")
                .firstName("Suvodeep").lastName("Biswas")
                .guardian(guardian).build();
        studentRepository.save(student);
    }
    @Test
    private void printAllStudent(){
        List<Student> studentList = studentRepository.findAll();

        System.out.println("studentList = " + studentList);
    }
    // for exact name
    @Test
    public void printStudentByFirstName(){
        List<Student> students = studentRepository.findByFirstName("Suvodeep");
        System.out.println("students = " + students);
    }
    // for char containing
    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students = studentRepository.findByFirstNameContaining("Su");
        System.out.println("students = " + students);
    }
    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> students = studentRepository.findByGuardianName("Siddyut");
    }
    @Test
    public void printGetStudentByEmailAddress(){
        Student student =
                studentRepository.getStudentByEmailAddress(
                        "wayne@gmail.com"
                );
        System.out.println("student = " + student);
    }
    @Test
    public void printGetStudentFirstNameByEmailAddress(){
        String name =
                studentRepository.getStudentFirstNameByEmailAddress(
                        "wayne@gmail.com"
                );
        System.out.println("student = " + name);
    }
    @Test
    // Native sql
    public void printgetStudentByEmailAddressNative(){
        Student student = studentRepository.getStudentByEmailAddressNative(
                "wayne@gmail.com"
        );
        System.out.println("student = " + student);
    }
    @Test
    // Native Named param
    public void printgetStudentByEmailAddressNativeNamedParam(){
        Student student = studentRepository.getStudentByEmailAddressNative(
                "wayne@gmail.com"
        );
        System.out.println("student = " + student);
    }
    // update in database
    @Test
    public void updateStudentNameByEmailIdTest(){
        studentRepository.updateStudentNameByEmailId(
                "Suvodeep",
                "wayne@gmail.com"
        );
    }

}