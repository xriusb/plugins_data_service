package com.xriusb.pluginsdataservice.service;

import com.xriusb.pluginsdataservice.model.Account;
import com.xriusb.pluginsdataservice.model.Device;
import com.xriusb.pluginsdataservice.model.HostResponse;
import com.xriusb.pluginsdataservice.repository.AccountRepository;
import com.xriusb.pluginsdataservice.service.viewcode.ViewCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PluginsDataService {

    private final AccountRepository accountRepository;
    private final ViewCode<String> alphanumericViewCode;

    public Optional<Account> findAccount(String code) {
        return accountRepository.findByCode(code);
    }

    public Optional<Device> findDevice(String accountCode, String deviceName) {
        return accountRepository.findDevice(accountCode, deviceName);
    }

    public HostResponse getHostResponse(Device device) {
        return HostResponse.builder()
                .hostName(device.getLoadBalancer().getHost())
                .pingTime(device.getPingTime())
                .viewCode(alphanumericViewCode.generate())
                .build();
    }
}
