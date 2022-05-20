package com.example.websiteblog.model;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class Comment {

    @Id
    private Long id;

    @NotEmpty(message = "Comment body can not be empty! Write something sane for the love of Internet, would you?")
    private String contentCmt;

//    @Temporal(TemporalType.TIMESTAMP)
//    @CreationTimestamp
//    @Column(name = "creation_date", nullable = false, updatable = false)
//    private Date creationDate;

    @NotNull
    private int postId;

    @NotNull
    private int userId;

    private Date createdTime;
    private Date updatedTime;
}