package com.xriusb.pluginsdataservice.api;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.xriusb.pluginsdataservice.model.Account;
import com.xriusb.pluginsdataservice.model.HostResponse;
import com.xriusb.pluginsdataservice.service.PluginsDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/dataService.com")
@RequiredArgsConstructor
public class PluginsDataAPI {
    private final PluginsDataService pluginsDataService;

    @GetMapping(value="/getData", produces= MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getPluginsData(@RequestParam String accountCode,
                                                 @RequestParam String targetDevice,
                                                 @RequestParam String pluginVersion) throws IOException {

        Optional<Account> account = pluginsDataService.findAccount(accountCode);
        if(!account.isPresent()) {
            return ResponseEntity.ok("");
        }

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        String xml = xmlMapper.writeValueAsString(HostResponse.builder().hostName("foo").pingTime(2).viewCode("baa").build());

        return ResponseEntity.ok(xml);
    }
}
