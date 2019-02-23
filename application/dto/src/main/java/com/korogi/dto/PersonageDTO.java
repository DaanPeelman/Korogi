package com.korogi.dto;

import static lombok.AccessLevel.PUBLIC;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = PUBLIC)
@NoArgsConstructor(access = PUBLIC)
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newPersonageDTO")
public class PersonageDTO extends BaseDTO {
    private static final String TYPE = "personage";

    private String firstName;
    private String lastName;
    private String photoUrl;

    @Override
    public String type() {
        return TYPE;
    }
}