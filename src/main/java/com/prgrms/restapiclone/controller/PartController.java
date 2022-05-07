package com.prgrms.restapiclone.controller;

import com.prgrms.restapiclone.Category;
import com.prgrms.restapiclone.dto.CreatePartRequest;
import com.prgrms.restapiclone.dto.PartResponse;
import com.prgrms.restapiclone.entity.Part;
import com.prgrms.restapiclone.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/parts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PartController {

    private final PartService partService;

    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PartResponse>> findParts(@RequestParam Category category) {
        List<Part> parts = partService.findParts(category);
        List<PartResponse> partResponses = convertToPartResponsesFromParts(parts);
        return ResponseEntity.ok(partResponses);
    }

    @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addParts(@RequestBody CreatePartRequest partRequest) {
        Long partId = partService.addPart(partRequest.toEntity());
        return new ResponseEntity(partId, HttpStatus.CREATED);
    }

    private List<PartResponse> convertToPartResponsesFromParts(List<Part> parts) {
        return parts.stream().map(PartResponse::from).collect(Collectors.toList());
    }
}
