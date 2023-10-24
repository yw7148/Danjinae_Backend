package com.capstone.danjinae.user.entity;

import io.swagger.models.auth.In;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class CertificateEmployment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path")
    private String filePath;

    @Builder
    public CertificateEmployment(Integer userId){
        this.userId= userId;
    }

    public void setFileName(String fileName){
        this.fileName=fileName;
    }

    public void setFilePath(String filePath){
        this.filePath=filePath;
    }

}
