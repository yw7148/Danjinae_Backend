package com.capstone.danjinae.notice.repository;

import com.capstone.danjinae.notice.entity.NoticeReadHistory;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeReadRepository extends JpaRepository<NoticeReadHistory, Integer> {
    List<NoticeReadHistory> findAllByUserIdAndNoticeId(Integer userId, Integer noticeId);
}