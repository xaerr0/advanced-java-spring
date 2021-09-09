package platform.codingnomads.co.springweb.wrappingup.multipartdata.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import platform.codingnomads.co.springweb.wrappingup.multipartdata.models.DatabaseFile;
import platform.codingnomads.co.springweb.wrappingup.multipartdata.services.FileService;

import java.nio.file.NoSuchFileException;

@RestController
@RequiredArgsConstructor
public class FileController {

    @Autowired
    FileService fileService;

    @PostMapping("/uploadSingleFile")
    public ResponseEntity<?> uploadFile(@RequestBody MultipartFile file) {
        try {
            return ResponseEntity.ok(fileService.saveFile(file));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFileById(@PathVariable(name = "id") Long fileId) {

        DatabaseFile databaseFile = fileService.getFile(fileId);

        if (databaseFile == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(databaseFile.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", databaseFile.getFileName()))
                    .body(new ByteArrayResource(databaseFile.getData()));
        }
    }

    @PutMapping("/uploadSingleFile/{id}")
    public ResponseEntity<?> updateFileById(@PathVariable(name = "id") Long fileId, @RequestBody MultipartFile file) {

        if (fileService.fileDoesNotExist(fileId)) {
            return ResponseEntity.badRequest()
                    .body(new NoSuchFileException("The ID you passed in was not valid. Where you trying to upload a new file?"));
        } else {
            try {
                return ResponseEntity.ok(fileService.updateFile(fileId, file));
            } catch (Exception ex) {
                return ResponseEntity.badRequest()
                        .body(ex.getMessage());
            }
        }
    }

    @DeleteMapping("/deleteFile/{id}")
    public ResponseEntity<?> deleteFileById(@PathVariable("id") Long fileId) {
        if (fileService.fileDoesNotExist(fileId)) {
            return ResponseEntity.badRequest()
                    .body(new NoSuchFileException("The ID you passed in was not valid."));
        } else {
            fileService.deleteFile(fileId);
            return ResponseEntity.ok("File with ID " + fileId + " was deleted.");
        }
    }
}

