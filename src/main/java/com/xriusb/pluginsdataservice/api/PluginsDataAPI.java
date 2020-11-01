package com.xriusb.pluginsdataservice.api;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.xriusb.pluginsdataservice.model.Account;
import com.xriusb.pluginsdataservice.model.Device;
import com.xriusb.pluginsdataservice.service.PluginsDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PluginsDataAPI {
    private final PluginsDataService pluginsDataService;
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

        return ResponseEntity.ok(xmlMapper.writeValueAsString(pluginsDataService.getHostResponse(device.get())));
    }

    @PostMapping("/accounts")
    public ResponseEntity createAccounts(@RequestBody List<Account> accounts) {
        return ResponseEntity.ok(pluginsDataService.save(accounts));
    }
}
