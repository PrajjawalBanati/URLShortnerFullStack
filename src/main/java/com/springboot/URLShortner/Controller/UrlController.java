package com.springboot.URLShortner.Controller;

import com.springboot.URLShortner.DTO.UrlShortnerDto;
import com.springboot.URLShortner.Model.Url;
import com.springboot.URLShortner.Service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.print.DocFlavor;

@CrossOrigin(origins = "*")
@Controller
@RestController
public class UrlController {
    @Autowired
    private UrlService urlService;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/url", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Url> shortenUrl(@RequestBody UrlShortnerDto urlShortnerDto) {
        Url shortUrlEntry = urlService.shorternUrl(urlShortnerDto.getLongUrl());
        return ResponseEntity.ok().body(shortUrlEntry);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/url/{key}")
    @ResponseBody
    public Url redirectURL(@PathVariable String key) {
        return urlService.getUrlByKey(key);
    }
}
