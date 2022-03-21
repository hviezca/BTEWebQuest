/*Controller file for Book Us functions
* Author @ Hiram Viezca
* */

package com.btewebquest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookingController {

    @GetMapping("/booking")
    public String bookUs(){
        return "booking-form";
    }
}
