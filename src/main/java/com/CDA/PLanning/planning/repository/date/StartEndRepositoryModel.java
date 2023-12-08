package com.CDA.PLanning.planning.repository.date;

import com.CDA.PLanning.planning.repository.absence.AbsenceRepositoryModel;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Getter
@Setter
@Table(name="startEnd")
public class StartEndrepositryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="start")
    private Date startDate;
    @Column(name="endDate")
    private Date endDate;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "absenceId")
    private AbsenceRepositoryModel absence;

}
