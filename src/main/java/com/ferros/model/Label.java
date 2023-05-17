package com.ferros.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString()
@Builder
@Entity
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "label_id")
    private Integer id;
    @Column(name = "label")
    private String name;

//    @Builder.Default
//    @OneToMany(mappedBy = "label")
//    private List<LabelPost> labelPosts =new ArrayList<>();
}
