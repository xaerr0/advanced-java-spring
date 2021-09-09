package platform.codingnomads.co.springweb.wrappingup.multipartdata.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import platform.codingnomads.co.springweb.wrappingup.multipartdata.models.DatabaseFile;
import platform.codingnomads.co.springweb.wrappingup.multipartdata.models.FileResponse;
import platform.codingnomads.co.springweb.wrappingup.multipartdata.repositories.DatabaseFileRepository;

import java.io.IOException;
import java.util.Objects;

@Service
public class FileService {

    @Autowired
    DatabaseFileRepository fileRepository;

    public DatabaseFile getFile(Long id) {
        if (fileRepository.findById(id).isEmpty()) {
            return null;
        } else {
            return fileRepository.findById(id).get();
        }
    }

    public FileResponse saveFile(MultipartFile multipartFile) throws Exception {
        validateMultiPartFile(multipartFile);
        final DatabaseFile savedFile = fileRepository.save(constructDatabaseFileFromMultiPart(multipartFile));
        return buildFileResponseFromDatabaseFile(savedFile);
    }

    public FileResponse updateFile(Long fileId, MultipartFile multipartFile) throws IOException {
        DatabaseFile databaseFile = fileRepository.findById(fileId).get();
        databaseFile.setData(multipartFile.getBytes());
        databaseFile.setFileName(multipartFile.getOriginalFilename());
        databaseFile.setFileType(multipartFile.getContentType());
        final DatabaseFile savedFile = fileRepository.save(databaseFile);
        return buildFileResponseFromDatabaseFile(savedFile);
    }

    public void deleteFile(Long fileId) {
        fileRepository.deleteById(fileId);
    }

    public boolean fileDoesNotExist(Long fileId) {
        return !fileRepository.existsById(fileId);
    }

    private void validateMultiPartFile(MultipartFile multipartFile) throws IllegalStateException {
        if (multipartFile.getOriginalFilename() == null) {
            throw new IllegalStateException("You must specify a file name!");
        } else if (multipartFile.isEmpty()) {
            throw new IllegalStateException("No file present!");
        }
    }

    private DatabaseFile constructDatabaseFileFromMultiPart(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        return DatabaseFile.builder()
                .data(file.getBytes())
                .fileName(fileName)
                .fileType(file.getContentType())
                .size(file.getSize())
                .build();
    }

    private FileResponse buildFileResponseFromDatabaseFile(DatabaseFile databaseFile) {
        return FileResponse.builder()
                .fileName(databaseFile.getFileName())
                .fileDownloadUri(databaseFile.getDownloadUrl())
                .fileType(databaseFile.getFileType())
                .size(databaseFile.getSize())
                .build();
    }
}
