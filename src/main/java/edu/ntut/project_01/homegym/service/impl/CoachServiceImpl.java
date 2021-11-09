package edu.ntut.project_01.homegym.service.impl;

import edu.ntut.project_01.homegym.model.Coach;
import edu.ntut.project_01.homegym.model.Course;
import edu.ntut.project_01.homegym.repository.CoachRepository;
import edu.ntut.project_01.homegym.service.CoachService;
import org.hibernate.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CoachServiceImpl implements CoachService {

    @Autowired
    private CoachRepository coachRepository;
    private Coach coach;

    @Override
    public Page<Course> findCourseByFilter(String courseName, Integer page, Integer size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return coachRepository.findByCourseName(courseName,pageRequest);
    }

    @Override
    public Integer getCoursesTotalPageByFilter(String courseName, Integer size){
        if(coachRepository.findCourseByCourseName(courseName).isPresent()){
            return (int)Math.ceil(coachRepository.findCourseByCourseName(courseName).get().size()/(double)size);
        }
        throw new QueryException("查無相關課程");
    }


    @Override
    public Optional<List<Course>> findCourseByCoachIdAndCourseName(Integer coachId,String courseName) {
        if (coachRepository.findCourseByCoachIdAndCourseName(coachId, courseName).isPresent()) {
            return coachRepository.findCourseByCoachIdAndCourseName(coachId, courseName);
        }
        throw new RuntimeException("請上傳課程");
    }


    @Override
    public ResponseEntity<List<Course>> findCoursesByKeyword(String keyword) {
        Optional<List<Course>> courseListByKeyword =  coachRepository.findCoursesByCourseNameContaining(keyword);
        if (courseListByKeyword.isPresent() && courseListByKeyword.get().size() != 0) {
            return ResponseEntity.ok().body( courseListByKeyword.get());
        }
        throw new QueryException("查無此關鍵字課程");
    }






    /*@Override
    public Set<Course> findByCoachId(Integer coachId) {

        return coachRepository.findByCoachId(coachId);
    }*/


    /*@Override
    public Optional<List<Course>> findCourseByCourseName(String courseName) {

        Optional<List<Course>> coach = coachRepository.findCourseByCourseName(courseName);
        if (courseName == null) {


        }
        return coachRepository.findCourseByCourseName(courseName);
    }*/


   /* @Override
    public Integer getAllCoursesTotalPage(Integer size) {
        return (int)Math.ceil(coachRepository.findAll().size()/(double)size);
    }*/




}


