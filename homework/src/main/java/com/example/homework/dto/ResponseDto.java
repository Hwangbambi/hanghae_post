package com.example.homework.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDto {
    private String msg;
    private int statusCode;
    //private Object data;

    public ResponseDto(String msg, int statusCode){
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
