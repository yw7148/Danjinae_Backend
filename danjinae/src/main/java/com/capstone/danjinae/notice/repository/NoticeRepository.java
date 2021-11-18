
package com.capstone.danjinae.notice.repository;

import com.capstone.danjinae.notice.entity.Notice;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    public Page<Notice> findByAptId(Integer aptId, Pageable pageable);
}
