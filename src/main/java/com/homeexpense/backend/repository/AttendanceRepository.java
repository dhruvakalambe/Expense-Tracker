package com.homeexpense.backend.repository;

import com.homeexpense.backend.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStaffId(Long staffId);
    List<Attendance> findByDate(LocalDate date);
    List<Attendance> findByStaffIdAndDateBetween(Long staffId, LocalDate start, LocalDate end);
    long countByStaffIdAndPresentIsFalse(Long staffId);
}
