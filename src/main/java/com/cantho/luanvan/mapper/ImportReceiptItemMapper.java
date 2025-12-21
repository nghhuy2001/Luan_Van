package com.cantho.luanvan.mapper;

import com.cantho.luanvan.dto.request.ImportReceiptItemDTO;
import com.cantho.luanvan.dto.request.ProductDTO;
import com.cantho.luanvan.entity.ImportReceiptItem;
import com.cantho.luanvan.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface ImportReceiptItemMapper {
    ImportReceiptItem toEntity(ImportReceiptItemDTO importReceiptItemDTO);

    @Mapping(source = "product", target = "productDTO")
    ImportReceiptItemDTO toDTO(ImportReceiptItem importReceiptItem);
}
