package com.ldar01.demoemployees.entities;

import com.ldar01.demoemployees.utils.enums.StatusType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Vacation", schema = "public")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vacation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "reason", columnDefinition = "TEXT")
    private String reason;

    @Column(name = "status")
    private StatusType status;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
}
