package com.prgrms.restapiclone.dto;

import com.prgrms.restapiclone.Category;
import com.prgrms.restapiclone.entity.Part;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PartResponse {

    private Long partId;
    private String name;
    private Long price;
    private Category category;

    public static PartResponse from(Part part) {
        return new PartResponse(part.getPartId(), part.getName(), part.getPrice(), part.getCategory());
    }
}
