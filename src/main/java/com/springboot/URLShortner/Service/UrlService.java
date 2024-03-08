package com.springboot.URLShortner.Service;

import com.springboot.URLShortner.Model.Url;
import jakarta.validation.constraints.NotBlank;

public interface UrlService {
    public Url getUrlByKey(@NotBlank String key);

    public Url shorternUrl(@NotBlank String url);
}
