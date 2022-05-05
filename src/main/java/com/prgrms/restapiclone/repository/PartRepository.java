package com.prgrms.restapiclone.repository;

import com.prgrms.restapiclone.Category;
import com.prgrms.restapiclone.entity.Part;

import java.util.List;
import java.util.Optional;

public interface PartRepository {

    Long save(Part part);

    Optional<Part> findById(Long computerPartId);

    List<Part> findByCategory(Category category);

}
