package com.sayantan.retail.exception;

import com.sayantan.retail.vo.BasicVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ExceptionVO extends BasicVO {

    private ErrorResponse errors;
}
