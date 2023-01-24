package com.example.z3;

import com.example.z3.controller.PointController;
import com.example.z3.point.Point;
import com.example.z3.service.PointService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PointController.class)
class PointTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PointService service;

    List<Point> points = Arrays.asList(new Point(2, 3), new Point(-2, 4), new Point(4, 1));

    @Test
    public void getPointsReturnsAllPoints() throws Exception {
        when(service.getPointsFromRepo()).thenReturn(points);

        mvc.perform(get("/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":0,\"x1\":2,\"x2\":3},{\"id\":0,\"x1\":-2,\"x2\":4},{\"id\":0,\"x1\":4,\"x2\":1}]"));
    }

    @Test
    public void getNPointsShouldReturnListOfNPoints() throws Exception {
        List<Point> pointsShort = Arrays.asList(new Point(2, 3), new Point(-2, 4));
        when(service.getNPoints(2)).thenReturn(pointsShort);

        mvc.perform(get("/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":0,\"x1\":2,\"x2\":3},{\"id\":0,\"x1\":-2,\"x2\":4}]"));
    }

    @Test
    public void getDistanceShouldReturnCorrectDistance() throws Exception {
        when(service.calculateDistance(0, 1)).thenReturn(4.1231056256177);

        mvc.perform(get("/distance/0/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("4.1231056256177"));
    }

    @Test
    public void addPointShouldReturnNewPointId() throws Exception {
        when(service.addPoint(new Point(1, 1))).thenReturn(0L);

        mvc.perform(post("/add").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(new Point(1, 1))))
                .andExpect(status().isCreated())
                .andExpect(content().json("0"));
    }

    @Test
    public void deletePointShouldReturnDeletedPointId() throws Exception {
        when(service.addPoint(new Point(1, 1))).thenReturn(0L);

        mvc.perform(delete("/delete/0").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("0"));
    }
}
