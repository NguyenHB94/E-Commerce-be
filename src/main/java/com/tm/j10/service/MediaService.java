package com.tm.j10.service;

import com.tm.j10.domain.Media;
import com.tm.j10.repository.MediaRepository;
import com.tm.j10.web.rest.vm.MediaVM;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@Service
public class MediaService {
    private MediaRepository mediaRepository;

    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    private void validateString(String str) {
        if (str == null ||
                str.length() == 0) throw new RuntimeException("Invalid string: " + str);
    }

    @Transactional
    public void uploadMedia(MediaVM mediaVM) {
        if (mediaVM != null) {
            String mediaAlt = mediaVM.getMediaAlt();
            validateString(mediaAlt);

            String mediaDes = mediaVM.getMediaDescription();
            validateString(mediaDes);

            String mediaCaption = mediaVM.getMediaCaption();
            validateString(mediaCaption);

            Media newMedia = new Media();

            if (mediaVM.getFile().isEmpty()) {
                throw new RuntimeException("File is null");
            }
            MultipartFile file = mediaVM.getFile();
            if (!file.isEmpty()) {
                try {
                    String path = System.getProperty("user.dir");
                    String realPathToUploadMedias = path + "\\upload";

                    if (!new File(realPathToUploadMedias).exists()) {
                        new File(realPathToUploadMedias).mkdirs();
                    }
                    LocalDateTime now = LocalDateTime.now();
                    String pattern = "yyyyMMdd hh-mm-ss";

                    String uploadedFileName = file.getName() + now.format(DateTimeFormatter.ofPattern(pattern));

                    String newPath = realPathToUploadMedias + "\\" + uploadedFileName;

                    File newFileObject = new File(newPath);
                    file.transferTo(newFileObject);

                    //Check file type
                    String fileType = file.getContentType();
                    String type = fileType.substring(fileType.length() - 3);
                    if (!type.equals("png") && !type.equals("jpg")) {
                        throw new RuntimeException("Invalid file type");
                    }

                    newMedia.setMediaURL(newPath);
                    newMedia.setMediaName(uploadedFileName);
                    newMedia.setMediaType(fileType);
                    newMedia.setUploadMonth(now.getMonth() + "");
                    newMedia.setUploadYear(now.getYear() + "");
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            newMedia.setMediaAlt(mediaAlt);
            newMedia.setMediaCaption(mediaCaption);
            newMedia.setMediaDescription(mediaDes);

            this.mediaRepository.save(newMedia);
        }
    }

    public Resource download(Long mediaId) throws MalformedURLException {
        if (mediaId == null || mediaId <= 0) {
            throw new RuntimeException("Invalid media Id");
        }
        Optional<Media> optionalMedia = mediaRepository.findById(mediaId);
        if (optionalMedia.isEmpty()) {
            throw new RuntimeException("Not found Media");
        }

        Media currentMedia = optionalMedia.get();
        Path path = Path.of(currentMedia.getMediaURL());

        Resource resource = new UrlResource(path.toUri());
        return resource;
    }
    @Transactional
    public Resource getMediaById(Long id)throws MalformedURLException, FileNotFoundException{
        var mediaFromDatabase = this.mediaRepository.findById(id);

        if(mediaFromDatabase.isPresent()){
            var realMedia = mediaFromDatabase.get();
            Path pathToFile = Path.of(realMedia.getMediaURL());

            File file = new File(realMedia.getMediaURL());
            InputStream inputStream = new FileInputStream(file);
            InputStreamSource inputStreamSource = new InputStreamResource(inputStream);
            Resource resource = new UrlResource(pathToFile.toUri());
            if (resource.exists()){
                return resource;
            }else {
                throw new RuntimeException("File not found !");
            }
        }else {
            return null;
        }
    }
}
