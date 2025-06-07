package com.moonstationery.model;

import java.text.Normalizer;

public class SlugUrl {
    public static String createSlug(String input) {
        if (input == null) return null;
        String slug = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[-\\s]+", "-")               // Replace spaces and hyphens with single hyphen
                .toLowerCase()
                .trim();
        return slug;
    }
}

