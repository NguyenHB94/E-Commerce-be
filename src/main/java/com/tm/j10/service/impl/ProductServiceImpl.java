package com.tm.j10.service.impl;

import com.tm.j10.domain.*;
import com.tm.j10.repository.*;
import com.tm.j10.service.ProductService;
import com.tm.j10.web.rest.vm.ColorVM;
import com.tm.j10.web.rest.vm.NewProductVM;
import com.tm.j10.web.rest.vm.ProductSizeVM;
import com.tm.j10.web.rest.vm.ProductVM;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Service Implementation for managing {@link Product}.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final StorageRepository storageRepository;

    private final CategoryRepository categoryRepository;

    private final ColorRepository colorRepository;

    private final ProductSizeRepository productSizeRepository;

    public ProductServiceImpl(UserRepository userRepository, ProductRepository productRepository, StorageRepository storageRepository, CategoryRepository categoryRepository, ColorRepository colorRepository, ProductSizeRepository productSizeRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.storageRepository = storageRepository;
        this.categoryRepository = categoryRepository;
        this.colorRepository = colorRepository;
        this.productSizeRepository = productSizeRepository;
    }

    @Override
    public Product save(Product product) {
        log.debug("Request to save Product : {}", product);
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        log.debug("Request to save Product : {}", product);
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> partialUpdate(Product product) {
        log.debug("Request to partially update Product : {}", product);

        return productRepository
            .findById(product.getId())
            .map(existingProduct -> {
                if (product.getName() != null) {
                    existingProduct.setName(product.getName());
                }
                if (product.getProductCode() != null) {
                    existingProduct.setProductCode(product.getProductCode());
                }
                if (product.getProductSKU() != null) {
                    existingProduct.setProductSKU(product.getProductSKU());
                }
                if (product.getPrice() != null) {
                    existingProduct.setPrice(product.getPrice());
                }
                if (product.getFinalPrice() != null) {
                    existingProduct.setFinalPrice(product.getFinalPrice());
                }
                if (product.getReleaseDateUnix() != null) {
                    existingProduct.setReleaseDateUnix(product.getReleaseDateUnix());
                }
                if (product.getReleaseType() != null) {
                    existingProduct.setReleaseType(product.getReleaseType());
                }
                if (product.getDesigner() != null) {
                    existingProduct.setDesigner(product.getDesigner());
                }
                if (product.getDescription() != null) {
                    existingProduct.setDescription(product.getDescription());
                }
                if (product.getModelHeight() != null) {
                    existingProduct.setModelHeight(product.getModelHeight());
                }
                if (product.getModelWeight() != null) {
                    existingProduct.setModelWeight(product.getModelWeight());
                }
                if (product.getMaterial() != null) {
                    existingProduct.setMaterial(product.getMaterial());
                }
                if (product.getSlug() != null) {
                    existingProduct.setSlug(product.getSlug());
                }
                if (product.getIsValid() != null) {
                    existingProduct.setIsValid(product.getIsValid());
                }
                if (product.getIsEnable() != null) {
                    existingProduct.setIsEnable(product.getIsEnable());
                }
                if (product.getStatus() != null) {
                    existingProduct.setStatus(product.getStatus());
                }
                if (product.getCreatedBy() != null) {
                    existingProduct.setCreatedBy(product.getCreatedBy());
                }
                if (product.getCreatedDate() != null) {
                    existingProduct.setCreatedDate(product.getCreatedDate());
                }
                if (product.getModifiedBy() != null) {
                    existingProduct.setModifiedBy(product.getModifiedBy());
                }
                if (product.getModifiedDate() != null) {
                    existingProduct.setModifiedDate(product.getModifiedDate());
                }

                return existingProduct;
            })
            .map(productRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        log.debug("Request to get all Products");
        return productRepository.findAll(pageable);
    }

    public Page<Product> findAllWithEagerRelationships(Pageable pageable) {
        return productRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findOne(Long id) {
        log.debug("Request to get Product : {}", id);
        return productRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Product : {}", id);
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> findByCategoryAndIsValid(Long categoryId, Boolean isValid, Pageable pageable){
        if (categoryId == 0) {
            throw new RuntimeException("Category Id can not be 0");
        }
        if (categoryId < 0) {
            throw new RuntimeException("Category Id can not be negative");
        }
        return this.productRepository.findByCategoryAndIsValid(categoryId, isValid, pageable);
    }

    @Override
    public List<Product> getProductsByCategoryName(String categoryName, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return this.productRepository.getByCategoryCategoryNameAndIsEnable(categoryName, true, pageable);
    }

    @Override
    public List<Product> search(String keyword, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        if (keyword != null) {
            return productRepository.search(keyword, pageable);
        }
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        // Step1: Query product by id where isEnable = true, isValid = true
        var getProduct = this.productRepository.findByIdAndIsValidAndIsEnable(id, true, true);
        if(getProduct.isEmpty()){
            throw new RuntimeException("Product is illegal");
        }

        // Step2: Query storage by product id, with join
        var getStorage = this.storageRepository.getProductAndColorAndProductSizeAndStore(id);
        ProductVM productVM = new ProductVM();
        BeanUtils.copyProperties(getProduct.get(), productVM);
        List<ProductVM.StorageVM> storageVMList = new ArrayList<>();
        for (Storage st: getStorage) {
            ProductVM.StorageVM tempVm = new ProductVM.StorageVM();
            tempVm.setProductSize(st.getProductSize());
            tempVm.setColor(st.getColor());
            tempVm.setStore(st.getStore());
            tempVm.setCapacity(st.getCapacity());
            storageVMList.add(tempVm);
        }
        productVM.setStorageVMS(storageVMList);

        return Optional.of(productVM);
    }

    @Override
    public void createNewProduct(NewProductVM newProductVM) {
        //validation
        if (newProductVM.getUserId() <= 0 || newProductVM.getUserId() == null) {
            throw new RuntimeException("UserId is invalid");
        }

        Optional<User> user = this.userRepository.findByIdAndAuthorities(newProductVM.getUserId());
        if (user.isEmpty()) {
            throw new RuntimeException("User is invalid");
        }

        if (newProductVM.getPrice() <= 0 || newProductVM.getPrice() == null) {
            throw new RuntimeException("Price of product is invalid");
        }

        if (newProductVM.getFinalPrice() <= 0 || newProductVM.getFinalPrice() == null) {
            throw new RuntimeException("Final Price of product is invalid");
        }

        if (newProductVM.getCategoryId() <= 0 || newProductVM.getCategoryId() == null) {
            throw new RuntimeException("CategoryId is invalid");
        }

        Optional<Category> category = this.categoryRepository.findByIdAndIsEnableIsTrue(newProductVM.getCategoryId());

        if (category.isEmpty()) {
            throw new RuntimeException("Category does not exits");
        }

        Set<ColorVM> colorVMSet = newProductVM.getColorVMSet();

        for (ColorVM c : colorVMSet) {
            if (c.getId() != null && c.getId() <= 0) {
                throw new RuntimeException("ColorId is invalid");
            }
        }

        Set<ProductSizeVM> productVMSet = newProductVM.getProductSizeVMSet();

        for (ProductSizeVM p : productVMSet) {
            if (p.getId() != null && p.getId() <= 0) {
                throw new RuntimeException("ProductSizeId is invalid");
            }
        }

        Product newProduct = new Product();
        newProduct.setName(newProductVM.getName());
        newProduct.setPrice(newProductVM.getPrice());
        newProduct.setFinalPrice(newProductVM.getFinalPrice());
        newProduct.setDesigner(newProductVM.getDesigner());
        newProduct.setDescription(newProductVM.getDescription());
        newProduct.setModelHeight(newProductVM.getModelHeight());
        newProduct.setModelWeight(newProductVM.getModelWeight());
        newProduct.setCategory(category.get());


        Set<Color> newColorList = new HashSet<>();
        Set<ProductSize> newProductSizeList = new HashSet<>();

        for (ColorVM c : colorVMSet) {
            if (c.getId() == null) {
                Color newColor = new Color();
                newColor.setColorName(c.getName());
                newColor.setDescription(c.getDescription());

                newColorList.add(newColor);
            }
        }

        for (ProductSizeVM p : productVMSet) {
            if (p.getId() == null) {
                ProductSize newProductSize = new ProductSize();
                newProductSize.setSizeName(p.getName());
                newProductSize.setDescription(p.getDescription());

                newProductSizeList.add(newProductSize);
            }
        }

        this.productRepository.save(newProduct);
        this.colorRepository.saveAll(newColorList);
        this.productSizeRepository.saveAll(newProductSizeList);
    }



}
