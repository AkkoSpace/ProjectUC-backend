package com.akko.projectucbackend.model;

import lombok.Data;

/**
 * @author akko
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
