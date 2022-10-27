package com.tm.j10.web.rest.vm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MediaVM {
    private String mediaAlt;

    private String mediaCaption;

    private String mediaDescription;

    private MultipartFile file;
}
