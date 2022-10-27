package com.tm.j10.web.rest.vm;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class CreateColorVM {

    @NotBlank(message = "colorName is not blank")
    private String colorName;

    private String description;

    @Pattern(regexp = "^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$", flags = Pattern.Flag.UNICODE_CASE)
    private String hexValue;
}
