package com.tm.j10.service;

import com.tm.j10.domain.Color;
import com.tm.j10.repository.ColorRepository;
import com.tm.j10.web.rest.errors.InvalidInputException;
import com.tm.j10.web.rest.vm.CreateColorVM;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorService {
    private final ColorRepository colorRepository;

    public ColorService(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    public List<Color> getAll() {
        return this.colorRepository.findAll();
    }

    public Color getById(Long id) {

        if (id <= 0) {
            throw new InvalidInputException("id is invalid!");
        }

        var color = this.colorRepository.findById(id);

        if (color.isEmpty()) {
            throw new RuntimeException("Color does not exist");
        }

        return color.get();
    }

    public Color createNewColor(CreateColorVM createColorVM) {
        Color newColor = new Color();

        newColor.setColorName(createColorVM.getColorName());
        newColor.setDescription(createColorVM.getDescription());
        newColor.setHexValue(createColorVM.getHexValue());

        return this.colorRepository.save(newColor);
    }

    public Color updateColor(Long id, CreateColorVM createColorVM) {
        if (id <= 0) {
            throw new InvalidInputException("id is invalid!");
        }

        var color = this.colorRepository.findById(id);

        if (color.isEmpty()) {
            throw new RuntimeException("Color does not exist!");
        }

        Color colorUpdate = color.get();

        colorUpdate.setColorName(createColorVM.getColorName());
        colorUpdate.setDescription(createColorVM.getDescription());
        colorUpdate.setHexValue(createColorVM.getHexValue());

        return this.colorRepository.save(colorUpdate);
    }

    public void deleteById(Long id) {
        if (id <= 0) {
            throw new InvalidInputException("id is invalid!");
        }

        var color = this.colorRepository.findById(id);

        if (color.isEmpty()) {
            throw new RuntimeException("Color does not exists!");
        }

        this.colorRepository.deleteById(id);
    }
}
