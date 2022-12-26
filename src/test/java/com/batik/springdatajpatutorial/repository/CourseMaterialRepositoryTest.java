package com.batik.springdatajpatutorial.repository;

import com.batik.springdatajpatutorial.entity.Course;
import com.batik.springdatajpatutorial.entity.CourseMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository repository;

    public void saveCourseMaterial(){

        Course course = Course.builder().tittle("DSA")
                .credit(6).build();
        CourseMaterial courseMaterial =
                CourseMaterial.builder().url("www.google.com").course(course).build();
        repository.save(courseMaterial);
    }

    public void printAllCourseMaterial(){
        List<CourseMaterial> courseMaterials =
                repository.findAll();
        System.out.println("courseMaterials = " + courseMaterials);
    }
}