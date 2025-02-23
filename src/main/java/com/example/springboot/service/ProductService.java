package com.example.springboot.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.springboot.controller.ProductController;
import com.example.springboot.dto.ProductRecordDto;
import com.example.springboot.model.ProductModel;
import com.example.springboot.repository.ProductRepository;

import jakarta.validation.Valid;

@Service
public class ProductService {

        private final ProductRepository productRepository;

        public ProductService(ProductRepository productRepository){
            this.productRepository = productRepository;
        }

        public List<ProductModel> allProduct(){
            List<ProductModel> productList = productRepository.findAll();

            if(!productList.isEmpty()){
                for(ProductModel product: productList){
                    product.add(linkTo(methodOn(ProductController.class).getOneProduct(product.getIdProduct())).withSelfRel());
                }
            }
            return productList;
        }

        public Optional<ProductModel> getOneProduct(UUID id){
            var product = productRepository.findById(id);
            if(product.isEmpty()){
                System.out.println("criar excessão");;
            }
            return product;
        }

        public ProductRecordDto saveProduct(ProductRecordDto productRecordDto){
            var productModel = new ProductModel();

            BeanUtils.copyProperties(productRecordDto, productModel);

            var product = productRepository.save(productModel);

            var ResponseProductRecordDto = new ProductRecordDto(null, null);

            BeanUtils.copyProperties(product, ResponseProductRecordDto);

            return ResponseProductRecordDto;
        }

        public Boolean deleteProduct(UUID id){
            var produc = productRepository.findById(id);

            if(produc.isEmpty()){
                return false;
            }else{
                productRepository.deleteById(id);
            }
            return true;
        }

        public Optional<ProductRecordDto> updateProduct(UUID id, ProductRecordDto productRecordDto) {
            var product0 = productRepository.findById(id);
            
            if (product0.isEmpty()) {
                System.out.println("Criar método de exceção");
                return Optional.empty(); // Retorna um Optional vazio se o produto não for encontrado
            }
        
            var productModel = product0.get(); 
        
            BeanUtils.copyProperties(productRecordDto, productModel);
        
            var productSaved = productRepository.save(productModel);
        
            ProductRecordDto responseProductRecordDto = new ProductRecordDto(productSaved.getName(), productSaved.getValue());
            BeanUtils.copyProperties(productSaved, responseProductRecordDto);
        
            return Optional.of(responseProductRecordDto);
        }
}
git config --global user.name "Eduardo"

git config --global user.email "eduardosilvamacedo96@gmail.com"