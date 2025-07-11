package com.homeexpense.backend.controller;

import com.homeexpense.backend.model.Staff;
import com.homeexpense.backend.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffRepository staffRepository;

    @PostMapping
    public Staff createStaff(@RequestBody Staff staff) {
        return staffRepository.save(staff);
    }

    @GetMapping
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }
}
