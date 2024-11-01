package com.micro.complaints.Repositories;

import com.micro.complaints.Entities.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {

    List<Complaint> findByEmail(String email); // Adjusted to match JPA method naming conventions

    List<Complaint> findByIdCompAndUserId(int idComp, int userId);

    List<Complaint> findByUserId(int userId);
}
