package com.xriusb.pluginsdataservice.api;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.xriusb.pluginsdataservice.model.Account;
import com.xriusb.pluginsdataservice.model.Device;
import com.xriusb.pluginsdataservice.model.HostResponse;
import com.xriusb.pluginsdataservice.service.PluginsDataService;
import com.xriusb.pluginsdataservice.service.viewcode.ViewCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/dataService.com")
@RequiredArgsConstructor
public class PluginsDataAPI {
    private final PluginsDataService pluginsDataService;
    private final ViewCode<String> alphanumericViewCode;
    private final XmlMapper xmlMapper = new XmlMapper();

    @PostConstruct
    private void setUpXMLMapper() {
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
    }

    @GetMapping(value="/getData", produces= MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getPluginsData(@RequestParam String accountCode,
                                                 @RequestParam String targetDevice,
                                                 @RequestParam String pluginVersion) throws IOException {

        Optional<Account> account = pluginsDataService.findAccount(accountCode);
        if(account.isEmpty()) {
            return ResponseEntity.ok("");
        }

        Optional<Device> device = pluginsDataService.findDevice(accountCode, targetDevice);
        if(device.isEmpty() || !device.get().getPluginVersion().equals(pluginVersion)) {
            return ResponseEntity.ok("");
        }

        HostResponse response = HostResponse.builder()
                .hostName(device.get().getLoadBalancer().getHost())
                .pingTime(device.get().getPingTime())
                .viewCode(alphanumericViewCode.generate())
                .build();

        return ResponseEntity.ok(xmlMapper.writeValueAsString(response));
    }
}
