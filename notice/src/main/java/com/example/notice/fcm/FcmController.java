package com.example.notice.fcm;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class FcmController {

        private final FirebaseInit init;

        @GetMapping("/v1")
        public String v1() {
            init.init();
            return "test";
        }
}

