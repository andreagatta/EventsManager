package org.elis.eventsmanager.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
public class CreatePlaceRequest {

    //PLACE
    @NotEmpty(message = "you have to insert an admin email")
    @Email(message = "admin email is not valid")
    private String adminEmail;
    @NotEmpty(message = "you have to insert an admin password")
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{6,15})", message = "admin password is not valid")
    private String adminPassword;
    @NotEmpty(message = "you have to insert a place name")
    private String placeName;
    @NotEmpty(message = "you have to insert a place way")
    private String placeWay;
    @NotEmpty(message = "you have to insert a place city")
    private String placeCity;
    @NotEmpty(message = "you have to insert a place province")
    private String placeProvince;
    @NotEmpty(message = "you have to insert a place CAP")
    private String placeCAP;

    //SECTIONS
    @NotBlank(message = "you have to insert some section for the place")
    private List<CreateSectionRequest> sections;




}
