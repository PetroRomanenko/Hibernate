package com.ferros.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "content")
@ToString(exclude = "labelPosts")
@Builder
@Entity
public class Post {
    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    private Date created;
    private Date updated;
    private PostStatus postStatus;

    @ManyToMany()
    @JoinTable(
            name="label_post",
            joinColumns = @JoinColumn (name = "post_id"),
            inverseJoinColumns = @JoinColumn (name = "label_id")
    )
    private List<Label> labels;




}
