package com.api.redis.model;

import lombok.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class User implements Serializable {

    private String userId;
    private String name;
    private String phone;
    private String email;

}
