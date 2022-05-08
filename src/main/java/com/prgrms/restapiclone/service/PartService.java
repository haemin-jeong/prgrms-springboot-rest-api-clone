package com.prgrms.restapiclone.service;

import com.prgrms.restapiclone.entity.Category;
import com.prgrms.restapiclone.entity.Part;

import java.util.List;

public interface PartService {

    List<Part> findParts(Category category);

    Long addPart(Part part);
}
