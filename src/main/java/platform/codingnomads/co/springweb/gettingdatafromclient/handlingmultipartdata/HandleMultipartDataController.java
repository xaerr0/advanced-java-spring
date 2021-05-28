package platform.codingnomads.co.springweb.gettingdatafromclient.handlingmultipartdata;

import lombok.RequiredArgsConstructor;
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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class HandleMultipartDataController {

    @Nonnull final DatabaseFileRepository fileRepository;

    @PostMapping("/uploadSingleFile")
    public FileResponse uploadFile(@RequestBody MultipartFile file) {
        System.out.println("placeholder");
        final String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            final DatabaseFile databaseFile = DatabaseFile.builder()
                    .data(file.getBytes())
                    .fileName(fileName)
                    .fileType(file.getContentType())
                    .build();

            final DatabaseFile savedFile = fileRepository.save(databaseFile);

            final String fileDownloadURI = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(String.valueOf(savedFile.getId()))
                    .toUriString();
            return FileResponse.builder()
                    .fileName(databaseFile.getFileName())
                    .fileDownloadUri(fileDownloadURI)
                    .fileType(file.getContentType())
                    .size(file.getSize())
                    .build();
        } catch (IOException ex) {
            throw new IllegalStateException("Sorry could not store file " + fileName + "Try again!", ex);
        }

    }

    @PostMapping("/uploadMultipleFiles")
    public List<FileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) throws IOException {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<Resource> downloadFileById(@PathVariable(name = "id") Long fileId) {
        final DatabaseFile databaseFile = fileRepository.findById(fileId)
                .orElseThrow(IllegalStateException::new);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(databaseFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", databaseFile.getFileName()))
                .body(new ByteArrayResource(databaseFile.getData()));

    }
}

