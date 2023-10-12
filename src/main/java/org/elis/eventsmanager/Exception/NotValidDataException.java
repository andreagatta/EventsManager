package org.elis.eventsmanager.Exception;

import lombok.Data;
import lombok.Getter;

import java.util.Map;

@Getter
public class NotValidDataException extends RuntimeException{

    Map<String,String> error;

    public NotValidDataException(Map<String,String> error){
        this.error=error;
    }
}