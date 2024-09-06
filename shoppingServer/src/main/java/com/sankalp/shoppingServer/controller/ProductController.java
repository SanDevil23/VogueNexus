package com.sankalp.shoppingServer.controller;


import com.sankalp.shoppingServer.exceptions.ResourceNotFoundException;
import com.sankalp.shoppingServer.model.Category;
import com.sankalp.shoppingServer.model.Product;
import com.sankalp.shoppingServer.requests.AddProductRequest;
import com.sankalp.shoppingServer.requests.UpdateProductRequest;
import com.sankalp.shoppingServer.response.ApiResponse;
import com.sankalp.shoppingServer.service.product.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@AllArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {

    private final IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(new ApiResponse( "Successful", products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error:", INTERNAL_SERVER_ERROR));
        }
    }


    @GetMapping("/product/{productId}/find")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
        try {
            Product product = productService.getProductById(productId);
            return ResponseEntity.ok(new ApiResponse( "Successful", product));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product) {
        try {
            Product theProduct  = productService.addProduct(product);
            return ResponseEntity.ok(new ApiResponse( "Product Added Successfully", theProduct));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/product/{productId}/update ")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody UpdateProductRequest request, @PathVariable Long productId) {
        try {
            Product theProduct  = productService.updateProduct(request, productId);
            return ResponseEntity.ok(new ApiResponse( "Product Updated Successfully", theProduct));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/product/{productId/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse( "Product Deleted Successfully", productId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/products/by/brand-and-name")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brandName, @RequestParam String productName) {
        try {
            List<Product> products = productService.getProductsByBrandAndName(brandName, productName);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No Product Found", null));
            }
            return ResponseEntity.ok(new ApiResponse( "Successful", products));
        } catch (Exception e ){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/products/by/category-and-brand")
    public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@RequestParam String category, @RequestParam String brand) {
        try {
            List<Product> products = productService.getProductsByCategoryAndBrand(category, brand);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No Product Found", null));
            }
            return ResponseEntity.ok(new ApiResponse( "Successful", products));
        } catch (Exception e ){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/products/{name}/products")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@PathVariable String name) {
        try {
            List<Product> products = productService.getProductsByName(name);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No Product Found", null));
            }
            return ResponseEntity.ok(new ApiResponse( "Successful", products));
        } catch (Exception e ){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }



    @GetMapping("/product/by-brand")
    public ResponseEntity<ApiResponse> findProductsByBrand(@RequestParam String brand){
        try{
            List<Product> products = productService.getProductsByBrand(brand);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No Product Found", null));
            }
            return ResponseEntity.ok(new ApiResponse( "Successful", products));
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse( e.getMessage(), null));
        }
    }



    @GetMapping("/product/{category}/all/products")
    public ResponseEntity<ApiResponse> findProductsByCategory(@PathVariable String category){
        try{
            List<Product> products = productService.getProductsByBrand(category);
            if (products.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No Product Found", null));
            }
            return ResponseEntity.ok(new ApiResponse( "Successful", products));
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse( e.getMessage(), null));
        }
    }


    @GetMapping("/product/count/by-brand/and-name")
    public ResponseEntity<ApiResponse> countProductsByBrandAndName(@RequestParam String brand, @RequestParam String name){
        try{
            Long productCount = productService.countProductsByBrandAndName(brand, name);

            return ResponseEntity.ok(new ApiResponse( "Successful", productCount));
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse( e.getMessage(), null));
        }
    }

}
