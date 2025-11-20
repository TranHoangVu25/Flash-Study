package com.example.language_practice.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    // Trong ảnh là integer(10) nhưng tag name nên là String
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<StudySet> studySets;
}
