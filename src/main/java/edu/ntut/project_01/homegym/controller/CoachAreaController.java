package edu.ntut.project_01.homegym.controller;


import edu.ntut.project_01.homegym.model.Course;
import edu.ntut.project_01.homegym.service.CoachService;
import edu.ntut.project_01.homegym.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
public class CoachAreaController {

    @Value("${course.countsPerPage}")
    private Integer size;

    @Autowired
    private CourseService courseService;



    /*
    @GetMapping("/coacharea/mycourse")
    public Optional<List<Course>> courseName(@RequestParam(required = false) Integer coachId, @RequestParam(required = false) String courseName){
        return coachService.findCourseByCoachIdAndCourseName(coachId,courseName);
    }


    @GetMapping("/coacharea/keyword")
    public ResponseEntity<List<Course>> keyword(@RequestParam(required = false) String keyword){
        return coachService.findCoursesByKeyword(keyword);
    }





*/

    @GetMapping("/coacharea/mycourse")
    ResponseEntity<Map<String, Object>> coachmycourse(@RequestParam(required = false) Integer page, @RequestParam Integer coachId){

        Page<Course> showCourse;
        Map<String, Object> storeDetail;


        showCourse = courseService.findCourseByCoachArea(coachId, 0, size);
        storeDetail = new HashMap<>();
        storeDetail.put("currentPage", showCourse.getContent());
        storeDetail.put("totalPage", showCourse.getTotalPages());
        return ResponseEntity.ok().body(storeDetail);
    }



    @GetMapping("/coacharea/keyword")
    public ResponseEntity<List<Course>> keyword(@RequestParam(required = false) String keyword){
        return courseService.findCoursesByKeyword(keyword);
    }



}
