package me.ivajgjakovikj.labfasd.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer availableCopies;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @ManyToOne
    private Author author;
}
