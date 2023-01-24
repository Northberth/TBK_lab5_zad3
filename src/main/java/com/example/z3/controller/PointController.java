package com.example.z3.controller;

import com.example.z3.point.Point;
import com.example.z3.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PointController {
    PointService pointService;

    @Autowired
    public PointController(PointService pointService) {
        this.pointService = pointService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Point>> getPoints() {
        List<Point> points = pointService.getPointsFromRepo();
        return new ResponseEntity<>(points, HttpStatus.OK);
    }

    @GetMapping("/{x}")
    public ResponseEntity<List<Point>> getFirstNPoints(@PathVariable int x) {
        List<Point> points = pointService.getNPoints(x);
        return new ResponseEntity<>(points, HttpStatus.OK);
    }

    @GetMapping("/distance/{x}/{y}")
    public ResponseEntity<Double> getDistance(@PathVariable int x, @PathVariable int y) {
        return new ResponseEntity<>(pointService.calculateDistance(x, y), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addPoint(@RequestBody Point point) {
        return new ResponseEntity<>(pointService.addPoint(point), HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Long> deletePoint(@PathVariable Long id) {
        return new ResponseEntity<>(pointService.deletePoint(id), HttpStatus.OK);
    }

//    docker run --rm -d --name jenkins -u root -p 8080:8080 -v jenkins-data:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock -v "/d/Studia/V semestr/tbk_lab5/jenkins":/home jenkins/jenkins
}
