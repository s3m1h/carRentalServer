package ksu.bitirmeserver.service.impl;

import ksu.bitirmeserver.service.ImageService;
import ksu.bitirmeserver.utilities.results.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class ImageServiceImpl implements ImageService {
    private final String FILE_PATH = "E:\\projects\\images\\storage\\";
    @Override
    public Result add(Long carID, MultipartFile file) throws IOException {
        String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        Path uploadPath = Path.of(FILE_PATH);
        Path filePath = uploadPath.resolve(uniqueFileName);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return null;
    }
}
