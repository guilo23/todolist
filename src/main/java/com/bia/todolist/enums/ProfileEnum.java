package com.bia.todolist.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  ProfileEnum {
    ADMIN(1,"ROW_ADMIN"),
    USER(2,"ROW_USER");
    private int code;
    private String description;

    public ProfileEnum toEnum(Integer code){
        if(code == null){
            return null;
        }
        for(ProfileEnum x : ProfileEnum.values()){
            if(code.equals(x.getCode())){
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid id: "+code);
    }
}
