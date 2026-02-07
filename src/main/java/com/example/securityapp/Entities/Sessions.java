package com.example.securityapp.Entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Sessions")
@Builder
public class Sessions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String refreshtoken;

    @CreationTimestamp
    LocalDateTime session_createdat;

    @ManyToOne
    UserEntity user;


}
