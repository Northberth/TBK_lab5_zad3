package com.example.z3.service;

import com.example.z3.point.Point;
import com.example.z3.repo.PointRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PointService {
    PointRepo pointRepo;

    @Autowired
    public PointService(PointRepo pointRepo) {
        this.pointRepo = pointRepo;

        pointRepo.save(new Point(1, 1));
        pointRepo.save(new Point(-1, 1));
        pointRepo.save(new Point(2, 2));
        pointRepo.save(new Point(4, 5));
        pointRepo.save(new Point(-2, 3));
        pointRepo.save(new Point(-3, 2));
        pointRepo.save(new Point(5, 10));

    }

    public List<Point> getPointsFromRepo() {
        return pointRepo.findAll();
    }

    public List<Point> getNPoints(int x) {
        return pointRepo.findAllFirstToNPoints(x);
    }

    public double calculateDistance(long aId, long bId) {
        Point a = pointRepo.getPointById(aId);
        Point b = pointRepo.getPointById(bId);

        return Math.sqrt(Math.pow((b.getX1() - a.getX1()), 2) + Math.pow(b.getX2() - a.getX2(), 2));
    }

    public long addPoint(Point point) {
        return pointRepo.save(point).getId();
    }

    public long deletePoint(long id) {
        pointRepo.deleteById(id);
        return id;
    }
}
