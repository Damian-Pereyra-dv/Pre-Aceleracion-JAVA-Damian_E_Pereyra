package com.alkemy.disney.disney.exception;

import com.alkemy.disney.disney.exception.msj.ErrorMessages;

public class ParamNotFound extends  RuntimeException {

    public  ParamNotFound (ErrorMessages errorMsg) {
        super(String.valueOf(errorMsg));
    }

}
