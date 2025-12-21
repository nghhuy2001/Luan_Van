package com.cantho.luanvan.service.impl;

import com.cantho.luanvan.dto.request.ImportReceiptDTO;
import com.cantho.luanvan.entity.*;
import com.cantho.luanvan.exception.common.ResourceNotFoundException;
import com.cantho.luanvan.mapper.ImportReceiptMapper;
import com.cantho.luanvan.repository.EmployeeRepository;
import com.cantho.luanvan.repository.ImportReceiptRepository;
import com.cantho.luanvan.repository.SupplierRepository;
import com.cantho.luanvan.service.domain.ImportReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImportReceiptServiceIMPL implements ImportReceiptService {
    private final ImportReceiptRepository importReceiptRepository;
    private final ImportReceiptMapper importReceiptMapper;
    private final SupplierRepository supplierRepository;
    private final EmployeeRepository employeeRepository;


    @Override
    public ImportReceipt initReceipt(Long supplierId, Long employeeId) {
        Supplier supplier = supplierRepository.getSupplierById(supplierId).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy nhà cing cấp với Id: "+supplierId)
        );
        Employee employee = employeeRepository.getEmployeeById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Không tìm thấy nhân viên với Id: "+employeeId)
        );
        return ImportReceipt.builder()
                .importDate(LocalDateTime.now())
                .supplier(supplier)
                .employee(employee)
                .build();
    }

    @Override
    public void addItem(ImportReceipt receipt, Product product, int quantity, BigDecimal unitPrice) {
        List<ImportReceiptItem> items = receipt.getImportReceiptItems();
        ImportReceiptItem item = ImportReceiptItem.builder()
                .importReceipt(receipt)
                .product(product)
                .quantityImport(quantity)
                .unitPrice(unitPrice)
                .build();
        if(items.isEmpty()){
            receipt.setImportReceiptItems(List.of(item));
        }else
            items.add(item);

        receipt.setImportReceiptItems(items);
        product.increaseStock(quantity);
    }

    @Override
    public ImportReceipt save(ImportReceipt receipt) {
        return importReceiptRepository.save(receipt);
    }

    @Override
    public Page<ImportReceiptDTO> getAllReceipt(Pageable pageable) {
        return importReceiptRepository.findAllWithItems(pageable).map(importReceiptMapper::toDTO);
    }
}
