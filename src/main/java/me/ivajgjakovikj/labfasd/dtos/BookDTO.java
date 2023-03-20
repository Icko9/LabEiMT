package me.ivajgjakovikj.labfasd.dtos;

import lombok.Data;

@Data
public class BookDTO {
    private Long id;
    private String name;
    private Integer availableCopies;
    private String category;
    private Long authorId;
    private String authorName;
    private String authorSurname;
}
