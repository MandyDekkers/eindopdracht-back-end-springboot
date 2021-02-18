package nl.eindopdracht.bootcamp.service;

import java.io.IOException;
import java.util.stream.Stream;

import nl.eindopdracht.bootcamp.model.AppUser;

import nl.eindopdracht.bootcamp.model.FileDB;
import nl.eindopdracht.bootcamp.repository.AppUserRepository;
import nl.eindopdracht.bootcamp.repository.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;

    @Autowired
    AppUserRepository appUserRepository;

    public FileDB store(MultipartFile file, long appuserId) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes());

        AppUser appUser = null;
        if(appUserRepository.existsById(appuserId)){
            appUser = appUserRepository.findById(appuserId).orElse(null);
        }
        fileDB.setAppuser(appUser);

        return fileDBRepository.save(fileDB);
    }

    public FileDB getFileById(String id){

        return fileDBRepository.findById(id).get();
    }

    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}