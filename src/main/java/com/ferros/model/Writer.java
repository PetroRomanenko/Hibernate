package com.ferros.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Writer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "writer_id")
    private int id;
    private String firstName;
    private String lastName;

    @ManyToMany
    @JoinTable(
            name = "post_writer",
            joinColumns = @JoinColumn (name = "writer_id" ),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private List<Post> posts;
}
