package com.cantho.luanvan.repository;

import com.cantho.luanvan.entity.ImportReceipt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportReceiptRepository extends JpaRepository<ImportReceipt, Long> {
    @Query("""
    SELECT DISTINCT ir
    FROM ImportReceipt ir
    LEFT JOIN FETCH ir.importReceiptItems iri
    LEFT JOIN FETCH iri.product p
""")
    Page<ImportReceipt> findAllWithItems(Pageable pageable);

}
