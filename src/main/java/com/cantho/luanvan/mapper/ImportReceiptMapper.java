package com.cantho.luanvan.mapper;

import com.cantho.luanvan.dto.request.ImportReceiptDTO;
import com.cantho.luanvan.entity.ImportReceipt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ImportReceiptItemMapper.class})
public interface ImportReceiptMapper {
    ImportReceipt toEntity(ImportReceiptDTO importReceiptDTO);

    @Mapping(source = "importReceipt.importReceiptItems", target = "importReceiptItemDTOS")
    @Mapping(source = "importReceipt.supplier.id", target = "supplierId")
    @Mapping(source = "importReceipt.employee.id", target = "employeeId")
    @Mapping(source = "importReceipt.totalPrice", target = "totalPrice")
    ImportReceiptDTO toDTO(ImportReceipt importReceipt);
}
