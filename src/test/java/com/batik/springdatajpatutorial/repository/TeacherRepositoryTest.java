package com.batik.springdatajpatutorial.repository;

import com.batik.springdatajpatutorial.entity.Course;
import com.batik.springdatajpatutorial.entity.Teacher;
import net.bytebuddy.utility.nullability.AlwaysNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher(){

        Course courseDSA = Course.builder()
                .tittle("DSA")
                .credit(5)
                .build();
        Course courseJava = Course.builder()
                .tittle("Java")
                .credit(6)
                .build();
        Teacher teacher =
                Teacher.builder()
                        .firstName("Pinaki")
                        .lastName("Paul")
//                        .courses(List.of(courseDSA, courseJava))
                        .build();
        teacherRepository.save(teacher);
    }
}