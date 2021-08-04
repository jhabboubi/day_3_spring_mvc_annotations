package org.perscholas.springapp.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {

    //fields
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @NotNull
    Integer id;
    @NotBlank
    String name;



}
