package com.homeexpense.backend.service;

import com.homeexpense.backend.model.Attendance;
import com.homeexpense.backend.model.Staff;
import com.homeexpense.backend.repository.AttendanceRepository;
import com.homeexpense.backend.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StaffRepository staffRepository;

    public Attendance markAttendance(Long staffId, boolean present) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found with id: " + staffId));

        Attendance attendance = new Attendance();
        attendance.setStaff(staff);
        attendance.setPresent(present);
        attendance.setDate(LocalDate.now());

        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAttendanceForStaff(Long staffId) {
        return attendanceRepository.findByStaffId(staffId);
    }

    public long countLeaves(Long staffId) {
        return attendanceRepository.countByStaffIdAndPresentIsFalse(staffId);
    }

    public Map<String, Long> getMonthlySummary(Long staffId, int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        List<Attendance> records = attendanceRepository.findByStaffIdAndDateBetween(staffId, start, end);

        long present = records.stream().filter(Attendance::isPresent).count();
        long absent = records.size() - present;

        Map<String, Long> summary = new HashMap<>();
        summary.put("presentDays", present);
        summary.put("absentDays", absent);
        return summary;
    }

    public Map<String, Map<String, Long>> getMonthlySummary(Long staffId) {
        List<Attendance> attendanceList = attendanceRepository.findByStaffId(staffId);

        return attendanceList.stream().collect(
                Collectors.groupingBy(
                        a -> a.getDate().getYear() + "-" + String.format("%02d", a.getDate().getMonthValue()),
                        Collectors.groupingBy(
                                a -> a.isPresent() ? "presentDays" : "absentDays",
                                Collectors.counting()
                        )
                )
        );
    }
}
