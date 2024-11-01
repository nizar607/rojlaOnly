package com.micro.complaints.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class ComplaintRequestModel {

        private int idComp;

        private String description;

        private TypeRec typeRec; // Assuming TypeRec is an Enum

        private String dateComplaint;

        private String name;

        private String lastname;

        private String email;

        private String message;

        private Integer userId;

        private ComplaintStatus status = ComplaintStatus.IN_PROGRESS;

        private SatisfactionLevel note;

}
