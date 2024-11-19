package com.guilherme.personalfinance.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.guilherme.personalfinance.models.enums.ProfileEnum;
import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = User.TABLE_NAME) //table for database
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    public static final String TABLE_NAME = "user";

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id is random (User id)
    private long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    @Size(min = 2, max = 100)
    @NotBlank //the username only can be created and he cant be null or empyt
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //write_only:front side don't have access of password
    @Column(name = "password", length = 60, nullable = false)
    @Size(min = 8, max = 60)
    @NotBlank
    private String password;

    @OneToMany(mappedBy = "user") // One user can have many tasks
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //not return
    private List<Finance> finance = new ArrayList<Finance>();

    @Column(name = "profile", nullable = false) //don't save a fake profile
    @ElementCollection(fetch = FetchType.EAGER) //When searching for the user, it will also search for the profile
    @CollectionTable(name = "user_profile")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //to ensure: the user don't receive his profiles
    private Set<Integer> profiles = new HashSet<>(); //List of unique values

    public Set<ProfileEnum> getProfiles() {
        return this.profiles.stream().map(x -> ProfileEnum.toEnum(x)).collect(Collectors.toSet());
        //transform the integer in profileEnum
        //It will transform the profile into a stream, mapping the values, taking the ProfileEnum,
        // passing the value (x) and transforming it into a .set(), returning the list of profiles
    }


    public void AddProfile(ProfileEnum profileEnum) { //add a profile for a user and saves
        this.profiles.add(profileEnum.getCode());
    }

}
