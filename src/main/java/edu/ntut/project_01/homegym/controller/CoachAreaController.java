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
    private CoachService coachService;




    @GetMapping("/coacharea/mycourse")
    public Optional<List<Course>> courseName(@RequestParam(required = false) Integer coachId, @RequestParam(required = false) String courseName){
        return coachService.findCourseByCoachIdAndCourseName(coachId,courseName);
    }


    @GetMapping("/coacharea/keyword")
    public ResponseEntity<List<Course>> keyword(@RequestParam(required = false) String keyword){
        return coachService.findCoursesByKeyword(keyword);
    }




    //課程分頁
    ResponseEntity<Map<String, Object>> CoachAreaCourse(@RequestParam(required = false) Integer page, @RequestParam(required = false) String courseName) {

        final Integer totalPage;
        Page<Course> showCourse;
        Map<String, Object> storeDetail;

        if (page != null && page > 0) {
            if (courseName == null) {
                totalPage = coachService.getCoursesTotalPageByFilter(courseName, size);
                if (page <= totalPage) {
                    showCourse = coachService.findCourseByFilter(courseName, page-1, size);
                    storeDetail = new HashMap<>();
                    storeDetail.put("currentPage", showCourse.getContent());
                    storeDetail.put("totalPage", showCourse.getTotalPages());
                    return ResponseEntity.ok().body(storeDetail);
                }
            }
            throw new NullPointerException("查無此頁面");
        } else {
            showCourse = coachService.findCourseByFilter(courseName, 0, size);
            storeDetail = new HashMap<>();
            storeDetail.put("currentPage", showCourse.getContent());
            storeDetail.put("totalPage", showCourse.getTotalPages());
            return ResponseEntity.ok().body(storeDetail);
        }
    }

}
