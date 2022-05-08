package com.prgrms.restapiclone.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class Part {

    private Long partId;
    private String name;
    private Category category;
    private long price;
    private LocalDateTime createdAt;

    public static Part of(Long partId, String name, Category category, long price, LocalDateTime createdAt) {
        return new Part(partId, name, category, price, createdAt);
    }

    public static Part of(String name, Category category, long price) {
        return new Part(null, name, category, price, null);
    }
}
