package com.sayantan.retail.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "error")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse
{
    private String message;

    private List<String> details;
}
