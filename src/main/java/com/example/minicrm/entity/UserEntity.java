package com.example.minicrm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonIgnore
    private String password;

    public String getPassword(){
        return password;
    }

    public String getEmail(){return email; }
    public String getUsername(){return username;}

    public void setPassword(String password){
        this.password = password;
    }
    public void setEmail(String email){this.email = email;}
    public void setUsername(String username){this.username = username;}

    public Long getId(){
        return id;
    }


}
