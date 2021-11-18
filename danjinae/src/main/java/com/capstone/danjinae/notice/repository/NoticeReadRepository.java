package com.capstone.danjinae.notice.repository;

import com.capstone.danjinae.notice.entity.NoticeReadHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeReadRepository extends JpaRepository<NoticeReadHistory, Integer> {
    NoticeReadHistory findByUserIdAndNoticeId(Integer userId, Integer noticeId);
}