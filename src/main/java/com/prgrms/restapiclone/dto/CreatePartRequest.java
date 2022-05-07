package com.prgrms.restapiclone.dto;

import com.prgrms.restapiclone.Category;
import com.prgrms.restapiclone.entity.Part;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreatePartRequest {

    private String name;

    private Long price;

    private Category category;

    public Part toEntity() {
        return Part.of(name, category, price);
    }
}
