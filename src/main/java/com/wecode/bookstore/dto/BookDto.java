package com.wecode.bookstore.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "BookDto", description = "Details of a book")
public class BookDto {

    @ApiModelProperty(
            readOnly = true,
            value = "UUID",
            dataType = "UUID",
            example = "65c502b6-a46d-49db-b964-87de17966fb7",
            notes = "Database generated uuid for book id",
            required = true
    )
    private UUID id;

    @ApiModelProperty(
            value = "title",
            dataType = "String",
            example = "Biology 1",
            notes = "Book title",
            required = true
    )
    private String title;

    @ApiModelProperty(
            value = "description",
            dataType = "String",
            example = "A comprehensive look at the cell's structure",
            notes = "Book description",
            required = true
    )
    private String description;

    @ApiModelProperty(
            value = "releaseYear",
            dataType = "int",
            example = "2021",
            notes = "Book release year",
            required = true
    )
    private int releaseYear;
}
