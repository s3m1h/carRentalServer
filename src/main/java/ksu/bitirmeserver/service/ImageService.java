package ksu.bitirmeserver.service;

import ksu.bitirmeserver.utilities.results.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    Result add(Long carID, MultipartFile file) throws IOException;
}
