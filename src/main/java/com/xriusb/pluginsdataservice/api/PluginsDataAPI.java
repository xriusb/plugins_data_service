package com.xriusb.pluginsdataservice.api;


import com.xriusb.pluginsdataservice.model.HostResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dataService.com")
public class PluginsDataAPI {

    @GetMapping(value="/getData", produces= MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<HostResponse> getPluginsData(@RequestParam String accountCode,
                                                       @RequestParam String targetDevice,
                                                       @RequestParam String pluginVersion) {
        return ResponseEntity.ok(HostResponse.builder().hostName("hola").pingTime(2).viewCode("adeu").build());
    }
}
