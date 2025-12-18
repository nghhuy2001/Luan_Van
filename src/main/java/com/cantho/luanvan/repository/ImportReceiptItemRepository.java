package com.cantho.luanvan.repository;

import com.cantho.luanvan.entity.ImportReceiptItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportReceiptItemRepository extends JpaRepository<ImportReceiptItem, Long> {
}
