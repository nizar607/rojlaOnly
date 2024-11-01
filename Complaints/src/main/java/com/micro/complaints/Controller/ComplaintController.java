package com.micro.complaints.Controller;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.micro.complaints.Entities.*;
import com.micro.complaints.Repositories.*;
import com.micro.complaints.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/complaint")
public class ComplaintController {


    @Autowired
    IProjectService iComplaintService;

    @PostMapping("/add")
    public ResponseEntity<Complaint> createComplaint(@RequestBody Complaint complaint) {
        try {
            complaint.setStatus(ComplaintStatus.IN_PROGRESS);
            Complaint newComplaint = iComplaintService.createComplaint(complaint);
            return new ResponseEntity<>(newComplaint, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/All")
    public ResponseEntity<List<Complaint>> getAllComplaints() {
        List<Complaint> complaints =iComplaintService.getAllComplaint();
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Complaint> updateComplaint(@PathVariable int id, @RequestBody ComplaintRequestModel updatedComplaint) {
        Optional<Complaint> complaintOpt = iComplaintService.getComplaintById(id);

        if (complaintOpt.isPresent()) {
            Complaint existingComplaint = complaintOpt.get();
            existingComplaint.setDescription(updatedComplaint.getDescription());
            existingComplaint.setTypeRec(updatedComplaint.getTypeRec());
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateComplaint = LocalDate.parse(updatedComplaint.getDateComplaint(), dateFormatter);
            existingComplaint.setDateComplaint(dateComplaint);
            existingComplaint.setName(updatedComplaint.getName());
            existingComplaint.setLastname(updatedComplaint.getLastname());
            existingComplaint.setEmail(updatedComplaint.getEmail());
            existingComplaint.setStatus(updatedComplaint.getStatus());
            existingComplaint.setMessage(updatedComplaint.getMessage());

            Complaint updated = iComplaintService.updateComplaint(existingComplaint);

            // Create response only if the status is "TREATED"
            if (existingComplaint.getStatus() == ComplaintStatus.TREATED) {
                Response response = new Response(updatedComplaint.getMessage(), existingComplaint.getIdComp());
                iComplaintService.createResponse(response);
            } else {
                System.out.println("No response created.");
            }

            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComplaint(@PathVariable int id) {
        iComplaintService.deleteComplaint(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
