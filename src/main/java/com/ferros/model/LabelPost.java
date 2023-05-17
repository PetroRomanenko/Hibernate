package com.ferros.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "label_post")
public class LabelPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Label label;

    @ManyToOne
    private Post post;

    public void setLabel(Label label){
        this.label = label;
        this.label.getLabelPosts().add(this);
    }

    public void setPost(Post post){
        this.post = post;
        this.post.getLabelPosts().add(this);
    }


}
