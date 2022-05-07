package com.prgrms.restapiclone.dto;

import com.prgrms.restapiclone.Category;
import com.prgrms.restapiclone.entity.Part;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class CreatePartRequest {

    @NotEmpty
    private String name;

    @NotNull
    @Min(0L)
    private Long price;

    @NotNull
    private Category category;

    public Part toEntity() {
        return Part.of(name, category, price);
    }
}
