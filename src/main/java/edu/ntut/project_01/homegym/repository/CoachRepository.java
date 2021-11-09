package edu.ntut.project_01.homegym.repository;


import edu.ntut.project_01.homegym.model.Coach;
import edu.ntut.project_01.homegym.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoachRepository extends JpaRepository<Coach,Integer> {
/*
    Optional<Set<Course>> findByCoachId(Integer coachId);

    Optional<List<Course>> findCourseByCourseName(String courseName);*/

    Optional<List<Course>> findCourseByCoachIdAndCourseName(Integer coachId,String courseName);

    Optional<List<Course>> findCourseByCourseName(String courseName);

    Page<Course> findByCourseName(String courseName, Pageable pageable);

    Optional<List<Course>> findCoursesByCourseNameContaining(String keyword);


}
