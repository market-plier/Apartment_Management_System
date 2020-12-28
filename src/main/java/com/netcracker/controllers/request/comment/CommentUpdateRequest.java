package com.netcracker.controllers.request.comment;

import lombok.Data;


import javax.validation.constraints.*;
import java.math.BigInteger;


@Data
public class CommentUpdateRequest {

    @NotNull(message = "comment cant be null")
    @NotBlank(message = "comment cant be blank")
    @Size(min = 1, max = 1000, message = "size must be between 1 and 1000")
    private String body;

    @NotNull(message = "Id cant be null")
    @Positive(message = "Id must be more than 0")
    private BigInteger commentId;

}
