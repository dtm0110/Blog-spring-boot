package com.example.websiteblog.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Post {

    private static final int MIN_TITLE_LENGTH = 7;

    @Id
    private Long id;

    private int userId;

    @NotEmpty(message = "Please enter the img")
    private String img;

    @Length(min = MIN_TITLE_LENGTH, message = "Title must be at least " + MIN_TITLE_LENGTH + " characters long")
    @NotEmpty(message = "Please enter the title")
    private String title;

    @NotEmpty(message = "Write something for the love of Internet...")
    private String contentPost;

    private Date createdTime;
    private Date updatedTime;
}
