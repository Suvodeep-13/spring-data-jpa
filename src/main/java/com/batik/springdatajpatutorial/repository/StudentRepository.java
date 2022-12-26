package com.batik.springdatajpatutorial.repository;

import com.batik.springdatajpatutorial.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.SimpleTimeZone;

// Repository use for to use different method (ex : for database)
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByFirstName(String firstName); // this is to get studnets by their name
    List<Student> findByFirstNameContaining(String name);
    List<Student> findByLastNameNotNull();
    List<Student> findByGuardianName(String guardianName);

    //JPQL(based on the classes but not as the table name)
    @Query("select s from Student s where s.emailId = $1")
    Student getStudentByEmailAddress(String EmailId);

    // if we want only firstName
    @Query("select s.firstName from Student s where s.emailId = $1")
     String getStudentFirstNameByEmailAddress(String EmailId);
    // for Native sql query
    @Query(
            value = "SELECT * FORM tbl_student s where s.email_address = ?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String EmailId);
    // Native Named param
    @Query(
            value = "SELECT * FORM tbl_student s where s.email_address = :emailId",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeNamedParam(@Param("emailId") String EmailId);

    // For update operation
    @Modifying
    @Transactional
    @Query(
            value = "update tbl_student set first_name = ?1 where email_address = ?2",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(String firstName, String emailId);
}
