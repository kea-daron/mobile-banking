package com.example.mobile_banking_api.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "medias")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 10)
    private String extension;

    @Column(nullable = false, length = 50)
    private String mimeTypeFile;

    @Column(nullable = false)
    private Boolean isDeleted;

}
