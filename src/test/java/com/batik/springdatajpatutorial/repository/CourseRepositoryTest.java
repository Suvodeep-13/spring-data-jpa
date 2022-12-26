package com.batik.springdatajpatutorial.repository;

import com.batik.springdatajpatutorial.entity.Course;
import com.batik.springdatajpatutorial.entity.CourseMaterial;
import com.batik.springdatajpatutorial.entity.Student;
import com.batik.springdatajpatutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses(){
        List<Course> courses =
                courseRepository.findAll();
        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithTeacher(){

        Teacher teacher = Teacher.builder()
                .firstName("Sumit")
                .lastName("Mondal")
                .build();
        Course course = Course.builder()
                .tittle("python")
                .credit(6)
                .teacher(teacher)
                .build();
        courseRepository.save(course);
    }

    // Pagging and shorting
    @Test
    public void findAllPagination(){
        Pageable firstPageWithThreeRecords =
                PageRequest.of(0, 3); // meaning every page should have 3 element
        Pageable secondPageWithTwoRecords =
                PageRequest.of(1, 2); // meaning every page should have 2 element

        List<Course> courses =
                courseRepository.findAll(firstPageWithThreeRecords)
                        .getContent();

        long totalElements =
                courseRepository.findAll(firstPageWithThreeRecords)
                                .getTotalElements();
        long totalPages =
                courseRepository.findAll(firstPageWithThreeRecords)
                                .getTotalPages();

        System.out.println("courses = " + courses);
    }

    @Test
    public void findAllSorting(){
        Pageable sortByTitle =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("title")
                );
        Pageable sortByCreditDesc =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("credit").descending()
                );
        Pageable sortByTitleAndCreditDesc =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("title").descending()
                                .and(Sort.by("credit"))
                );

        List<Course>  courses =
                courseRepository.findAll(sortByTitle).getContent();
        System.out.println("courses = " + courses);
    }

    public void printfindByTitleContaining(){
        Pageable firstPageTenRecords =
                PageRequest.of(0, 10);
        List<Course> courses =
                courseRepository.findByTitleContaining("D",
                        firstPageTenRecords).getContent();
    }

    @Test
    public void saveCourseWithStudentAndTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("wayne")
                .lastName("Naik")
                .build();

        Student student = Student.builder()
                .firstName("Rahul")
                .lastName("Biswas")
                .emailId("rahul@gamil.com")
                .build();

        Course course = Course.builder()
                .tittle("AI")
                .credit(12)
                .teacher(teacher)
                .build();
        course.addStudents(student);

        courseRepository.save(course);
    }
}