package edu.ntut.project_01.homegym.service;

import edu.ntut.project_01.homegym.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CoachService {
/*

    Set<Course> findByCoachId (Integer coachId);
    Optional<List<Course>> findByCourseName(String courseName);
*/





    ResponseEntity<List<Course>> findCoursesByKeyword(String keyword);  //找關鍵字

    Optional<List<Course>> findCourseByCoachIdAndCourseName(Integer coachId,String courseName);   //從課程找教練id


//  Integer getAllCoursesTotalPage(Integer size);

    Page<Course> findCourseByFilter(String courseName, Integer page, Integer size);

    Integer getCoursesTotalPageByFilter(String courseName, Integer size);

}
