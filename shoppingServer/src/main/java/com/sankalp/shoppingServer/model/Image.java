package com.sankalp.shoppingServer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String fileName;
    private String fileType;
    @Lob
    private Blob image;
    private String downloadUrl;


    // mapping Many to One
    // Many images belong to One Product
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
