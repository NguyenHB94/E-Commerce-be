package com.tm.j10.web.rest;

import com.tm.j10.domain.Color;
import com.tm.j10.service.ColorService;
import com.tm.j10.web.rest.vm.CreateColorVM;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/colors")
public class ColorController {

    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping
    public ResponseEntity<List<Color>> getAll() {
        var colorList = this.colorService.getAll();
        return ResponseEntity.ok(colorList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Color> getById(@PathVariable("id") Long id) {
        var color = this.colorService.getById(id);
        return ResponseEntity.ok(color);
    }

    @PostMapping
    public ResponseEntity<Color> createNewColor(@RequestBody @Valid CreateColorVM createColorVM) {
        var color = this.colorService.createNewColor(createColorVM);
        return ResponseEntity.created(URI.create("/")).body(null);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateColor(@PathVariable("id") Long id,
                                            @RequestBody @Valid CreateColorVM createColorVM) {
        var color = this.colorService.updateColor(id, createColorVM);
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColor(@PathVariable("id") Long id) {
        this.colorService.deleteById(id);
        return ResponseEntity.ok().body(null);
    }


}
