package com.upload.arquivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upload.arquivo.entity.Upload;

@Repository
public interface UploadRepository extends JpaRepository<Upload, Long> {

}
