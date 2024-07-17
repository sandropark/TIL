package com.sandro.springbatch.core.repository;

import com.sandro.springbatch.core.domain.PlainText;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlainTextRepository extends JpaRepository<PlainText, Long> {
    Page<PlainText> findBy(Pageable pageable);
}
