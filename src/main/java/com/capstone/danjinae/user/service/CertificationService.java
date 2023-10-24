package com.capstone.danjinae.user.service;

import com.capstone.danjinae.user.entity.CertificateEmployment;
import com.capstone.danjinae.user.repository.CertificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class CertificationService {

    @Autowired
    private CertificationRepository certificationRepository;

    public void upload(CertificateEmployment certification, MultipartFile file) throws Exception{

        String savePath= System.getProperty("user.dir") + "\\src\\main\\resources\\static\\file";
        UUID uuid= UUID.randomUUID();
        String fileName= uuid+ "_"+file.getOriginalFilename();
        File saveFile= new File(savePath, fileName);
        file.transferTo(saveFile);

        certification.setFileName(fileName);
        certification.setFilePath("/file/"+fileName);

        certificationRepository.save(certification);
    }
}
