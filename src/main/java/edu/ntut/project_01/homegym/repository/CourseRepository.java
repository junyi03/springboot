package edu.ntut.project_01.homegym.repository;

import edu.ntut.project_01.homegym.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CourseRepository extends JpaRepository<Course,Integer> {

    Page<Course> findCourseByCoach_CoachId(Integer coachId, Pageable pageable);




    Optional<List<Course>> findCourseByPartOfBody(String partOfBody);   // <List<Course>>指定list只放course
    Page<Course> findByPartOfBody(String partOfBody, Pageable pageable);



    Optional<List<Course>> findCoursesByCourseNameContaining(String keyword);   //一個interface

}
