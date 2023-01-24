package com.example.z3.repo;

import com.example.z3.point.Point;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PointRepo extends CrudRepository<Point, Long> {
    List<Point> findAll();

    @Query(value = "select * from Point limit ?1", nativeQuery = true)
    List<Point> findAllFirstToNPoints(@Param("x") int x);

    Point getPointById(long id);
}
