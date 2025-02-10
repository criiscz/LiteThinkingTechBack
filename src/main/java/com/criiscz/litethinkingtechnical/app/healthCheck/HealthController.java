package com.criiscz.litethinkingtechnical.app.healthCheck;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
@AllArgsConstructor
public class HealthController {

        @RequestMapping(value = "", method= RequestMethod.GET, produces = "text/plain")
        public String check() {
            return "OK";
        }
}
