package com.ahmedukamel.problemsolver.dto;

import com.ahmedukamel.problemsolver.validation.UniquePhone;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ahmedukamel.problemsolver.validation.ValidationMessages.*;
import static com.ahmedukamel.problemsolver.validation.ValidationRegexp.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    @Pattern(message = INVALID_NAME_MESSAGE, regexp = REGEXP_NAME)
    private String name;
    @Pattern(message = INVALID_TITLE_MESSAGE, regexp = REGEXP_TITLE)
    private String title;
    @Size(message = INVALID_BIO_MESSAGE, min = 10)
    private String bio;
    private String picture;

}
