package com.xriusb.pluginsdataservice.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xriusb.pluginsdataservice.model.Account;
import com.xriusb.pluginsdataservice.model.Device;
import com.xriusb.pluginsdataservice.service.loadbalancer.WeightedLoadBalancer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class AccountRepository {

    private List<Account> accounts;

    @PostConstruct
    private void loadInitialAccounts() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Account>> typeReference = new TypeReference<>(){};
        accounts = mapper.readValue(new File("src/main/resources/data/accounts.json"), typeReference);
        accounts.forEach(account -> account.getDevices()
                .forEach(device -> device.setLoadBalancer(new WeightedLoadBalancer(device.getCluster()))));
    }

    public Optional<Account> findByCode(String code) {
        return accounts.stream().filter(account -> account.getCode().equals(code)).findFirst();
    }

    public Optional<Device> findDevice(String accountCode, String deviceName) {
        return accounts.stream()
                .filter(account -> account.getCode().equals(accountCode))
                .flatMap(account -> account.getDevices().stream())
                .filter(device -> device.getName().equals(deviceName))
                .findFirst();
    }
}
