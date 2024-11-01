package com.micro.complaints.Repositories;

import com.micro.complaints.Entities.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ResponseRepository extends JpaRepository<Response, Integer> {
}
