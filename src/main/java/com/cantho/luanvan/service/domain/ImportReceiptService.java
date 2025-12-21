package com.cantho.luanvan.service.domain;

import com.cantho.luanvan.dto.request.ImportReceiptDTO;
import com.cantho.luanvan.entity.ImportReceipt;
import com.cantho.luanvan.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface ImportReceiptService {
    ImportReceipt initReceipt(Long supplierId, Long employeeId);
    void addItem(ImportReceipt receipt, Product product, int quantity, BigDecimal unitPrice);
    ImportReceipt save(ImportReceipt receipt);
    /*-------------------------------------------------------------------------------------------*/
    Page<ImportReceiptDTO> getAllReceipt(Pageable pageable);
}
