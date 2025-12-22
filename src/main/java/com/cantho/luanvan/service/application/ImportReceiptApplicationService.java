package com.cantho.luanvan.service.application;

import com.cantho.luanvan.dto.request.ImportReceiptDTO;
import com.cantho.luanvan.dto.request.ImportReceiptItemDTO;
import com.cantho.luanvan.entity.ImportReceipt;
import com.cantho.luanvan.entity.ImportReceiptItem;
import com.cantho.luanvan.entity.Product;
import com.cantho.luanvan.exception.common.ResourceNotFoundException;
import com.cantho.luanvan.mapper.ImportReceiptMapper;
import com.cantho.luanvan.repository.ProductRepository;
import com.cantho.luanvan.service.domain.ImportReceiptService;
import com.cantho.luanvan.service.domain.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImportReceiptApplicationService {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final ImportReceiptService importReceiptService;
    private final ImportReceiptMapper importReceiptMapper;


    @Transactional
    public ImportReceiptDTO createImportReceipt(ImportReceiptDTO importReceiptDTO){
        ImportReceipt importReceipt = importReceiptService.initReceipt(
                importReceiptDTO.getSupplierId(),
                importReceiptDTO.getEmployeeId()
        );
        List<ImportReceiptItem> receiptItemList = new ArrayList<>();
        for (ImportReceiptItemDTO item : importReceiptDTO.getImportReceiptItemDTOS()){
            Product product;
            if(item.getProductDTO().getId() == null){
                product = productService.createProduct(item.getProductDTO());
            }else {
                product = productRepository.getProductById(item.getProductDTO().getId()).orElseThrow(
                        () -> new ResourceNotFoundException("Không tìm thấy sản phẩm với ID: "+item.getProductDTO().getId())
                );
                product.increaseStock(item.getQuantityImport());
                product.setPrice(item.getUnitPrice());
            }


            ImportReceiptItem importReceiptItem = ImportReceiptItem.builder()
                    .importReceipt(importReceipt)
                    .product(product)
                    .quantityImport(item.getQuantityImport())
                    .unitPrice(item.getUnitPrice())
                    .build();
            receiptItemList.add(importReceiptItem);
            importReceipt.sumTotalPrice(item.getUnitPrice(), item.getQuantityImport());
        }
        importReceipt.setImportReceiptItems(receiptItemList);
        ImportReceipt saved = importReceiptService.save(importReceipt);
        return importReceiptMapper.toDTO(saved);
    }
}
