package com.prgrms.restapiclone.service;

import com.prgrms.restapiclone.entity.Category;
import com.prgrms.restapiclone.entity.Part;
import com.prgrms.restapiclone.repository.PartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;

    @Override
    public List<Part> findParts(Category category) {
        return partRepository.findByCategory(category);
    }

    @Override
    public Long addPart(Part part) {
        return partRepository.save(part);
    }
}
