package com.tm.j10.web.rest;

import com.tm.j10.service.MediaService;
import com.tm.j10.web.rest.vm.MediaVM;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

@RestController
@RequestMapping("/api/v1/medias")
public class MediaController {
    private MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping("")
    public ResponseEntity<String> uploadMedia(MediaVM mediaVM) {
        this.mediaService.uploadMedia(mediaVM);
        return ResponseEntity.ok("Upload succeed");
    }

    @GetMapping("/{mediaId}")
    public ResponseEntity<Resource> download(@PathVariable("mediaId") Long mediaId) throws MalformedURLException {
        Resource resource = this.mediaService.download(mediaId);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(resource);
    }
    @GetMapping("/medias/{id}")
    public ResponseEntity<Resource> getMediaById(@PathVariable("id") Long id) throws MalformedURLException, FileNotFoundException {
        Resource resource = this.mediaService.getMediaById(id);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
    }
}
