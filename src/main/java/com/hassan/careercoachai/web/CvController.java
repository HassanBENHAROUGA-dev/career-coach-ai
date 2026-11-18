package com.hassan.careercoachai.web;

import com.hassan.careercoachai.dto.CvExtractionResponse;
import com.hassan.careercoachai.service.CvService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("/api/cvs")
public class CvController {

    private final CvService cvService;

    public CvController(CvService cvService) {
        this.cvService = cvService;
    }

    @PostMapping(
            value = "/extract",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public CvExtractionResponse extract(
            @RequestParam("file") MultipartFile file
    ) {
        byte[] content;

        try {
            content = file.getBytes();
        } catch (IOException exception) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Impossible de recevoir le fichier.",
                    exception
            );
        }

        return new CvExtractionResponse(cvService.extractText(content));
    }
}