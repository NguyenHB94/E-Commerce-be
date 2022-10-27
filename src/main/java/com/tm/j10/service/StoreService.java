package com.tm.j10.service;

import com.tm.j10.domain.Store;
import com.tm.j10.repository.StoreRepository;
import io.undertow.servlet.core.ApplicationListeners;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    private StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<Store> getAllStore() {
        return this.storeRepository.findAll();
    }

    public Optional<Store> getOneId(long id) {
        Optional<Store> store = this.storeRepository.findById(id);
        return store;
    }

    public Store createStore(Store stores) {
        var ret = this.storeRepository.save(stores);
        return ret;
    }

    public void deleteStore(long id) {
        this.storeRepository.deleteById(id);
    }

    public boolean update(long id, Store store) {
        var ret = this.storeRepository.findById(id);
        if (ret.isPresent()) {
            if (store.getStoreName() != null && !store.getStoreName().isEmpty()) {
                ret.get().setStoreName(store.getStoreName());
            }
            if (store.getAddress() != null && !store.getAddress().isEmpty()) {
                ret.get().setAddress(store.getAddress());
            }
            if (store.getEmail() != null && !store.getEmail().isEmpty()) {
                ret.get().setEmail(store.getEmail());
            }
            if (store.getDescription() != null && !store.getDescription().isEmpty()) {
                ret.get().setDescription(store.getDescription());
            }
            if (store.getManager() != null && !store.getManager().isEmpty()) {
                ret.get().setManager(store.getManager());
            }
            if (store.getTel() != null && !store.getTel().isEmpty()) {
                ret.get().setTel(store.getTel());
            }
            if (store.getWorkingHour() != null && !store.getWorkingHour().isEmpty()) {
                ret.get().setWorkingHour(store.getWorkingHour());
            } else {
                return false;
            }
        }
        return false;
    }
}
