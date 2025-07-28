package com.homeexpense.backend.controller;

import com.homeexpense.backend.model.Attendance;
import com.homeexpense.backend.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/{staffId}")
    public Attendance mark(@PathVariable Long staffId, @RequestParam boolean present) {
        return attendanceService.markAttendance(staffId, present);
    }

    @GetMapping("/{staffId}")
    public List<Attendance> getAttendance(@PathVariable Long staffId) {
        return attendanceService.getAttendanceForStaff(staffId);
    }

    @GetMapping("/{staffId}/leaves")
    public long getLeaveCount(@PathVariable Long staffId) {
        return attendanceService.countLeaves(staffId);
    }

    @GetMapping("/{staffId}/summary")
    public Map<String, Long> getMonthlySummary(
            @PathVariable Long staffId,
            @RequestParam int year,
            @RequestParam int month) {
        return attendanceService.getMonthlySummary(staffId, year, month);
    }

    @GetMapping("/{staffId}/monthly-summary")
    public Map<String, Map<String, Long>> getMonthlySummary(@PathVariable Long staffId) {
        return attendanceService.getMonthlySummary(staffId);
    }
}
