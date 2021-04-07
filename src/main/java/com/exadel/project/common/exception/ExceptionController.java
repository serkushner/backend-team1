package com.exadel.project.common.exception;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * It is used to call custom exceptions and check response on them.
 */
@RestController
@RequestMapping("exception")
public class ExceptionController {

    @GetMapping("/{exception_id}")
    public void getSpecificException(@PathVariable ("exception_id") String id) {
        if("1".equals(id)) {
            throw new EntityNotFoundException();
        }
        else if("2".equals(id)) {
            throw new EntityAlreadyExistsException();
        }
        else {
            throw new UnsupportedOperationException();
        }
    }
}
