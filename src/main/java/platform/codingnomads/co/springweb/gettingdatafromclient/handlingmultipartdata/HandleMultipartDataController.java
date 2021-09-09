package platform.codingnomads.co.springweb.gettingdatafromclient.handlingmultipartdata;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class HandleMultipartDataController {

    @Autowired
    DatabaseFileRepository fileRepository;

    @PostMapping("/uploadSingleFile")
    public ResponseEntity<?> uploadFile(@RequestBody MultipartFile file) {

        final String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            final DatabaseFile databaseFile = DatabaseFile.builder()
                    .data(file.getBytes())
                    .fileName(fileName)
                    .fileType(file.getContentType())
                    .build();

            final DatabaseFile savedFile = fileRepository.save(databaseFile);

            final String fileDownloadURI = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(String.valueOf(savedFile.getId()))
                    .toUriString();

            return ResponseEntity.ok(FileResponse.builder()
                    .fileName(databaseFile.getFileName())
                    .fileDownloadUri(fileDownloadURI)
                    .fileType(file.getContentType())
                    .size(file.getSize())
                    .build());
        } catch (IOException ex) {
            return ResponseEntity.badRequest().body(new IllegalStateException("Sorry could not store file " + fileName + "Try again!", ex));
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFileById(@PathVariable(name = "id") Long fileId) {
        final Optional<DatabaseFile> optional = fileRepository.findById(fileId);

        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        DatabaseFile databaseFile = optional.get();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(databaseFile.getFileType()))
                // display the file inline
//                .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                // download file, without setting file name
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment")
                // download file, and specify file name
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", databaseFile.getFileName()))
                .body(new ByteArrayResource(databaseFile.getData()));
    }

    @PutMapping("/uploadSingleFile/{id}")
    public ResponseEntity<?> updateFileById(@PathVariable(name = "id") Long fileId, @RequestBody MultipartFile file) {
        final Optional<DatabaseFile> optional = fileRepository.findById(fileId);

        if (optional.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new NoSuchFileException("The ID you passed in was not valid. Where you trying to upload a new file?"));
        }

        DatabaseFile databaseFile = optional.get();
        try {
            databaseFile.setData(file.getBytes());
            databaseFile.setFileName(file.getOriginalFilename());
            databaseFile.setFileType(file.getContentType());
        } catch (IOException ex) {
            return ResponseEntity.badRequest()
                    .body(new IllegalStateException("Sorry could not update file " + file.getOriginalFilename() + "Try again!", ex));
        }

        final DatabaseFile savedFile = fileRepository.save(databaseFile);

        final String fileDownloadURI = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(String.valueOf(savedFile.getId()))
                .toUriString();

        return ResponseEntity.ok(FileResponse.builder()
                .fileName(databaseFile.getFileName())
                .fileDownloadUri(fileDownloadURI)
                .fileType(file.getContentType())
                .size(file.getSize())
                .build());
    }

    @DeleteMapping("/deleteFile/{id}")
    public ResponseEntity<?> deleteFileById(@PathVariable("id") Long fileId) {
        final Optional<DatabaseFile> optional = fileRepository.findById(fileId);

        if (optional.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new NoSuchFileException("The ID you passed in was not valid."));
        }

        fileRepository.deleteById(fileId);
        return ResponseEntity.ok("File with ID " + fileId + " and name " + optional.get().getFileName() + " was deleted");
    }
}

