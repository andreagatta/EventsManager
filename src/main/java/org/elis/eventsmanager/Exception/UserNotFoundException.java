package org.elis.eventsmanager.Exception;

import lombok.Data;
import lombok.Getter;

import java.util.Map;

@Getter
public class UserNotFoundException extends RuntimeException{

public UserNotFoundException(){
    super("No user found with this credentials. Please try again");
}
}