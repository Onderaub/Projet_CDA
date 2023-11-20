package com.CDA.PLanning.human.repository.manager;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.CrudRepository;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="manager")
public class ManagerRepositoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idManager")
    private Long id;
}
