package com.xriusb.pluginsdataservice.service;

import com.xriusb.pluginsdataservice.model.Account;
import com.xriusb.pluginsdataservice.model.Device;
import com.xriusb.pluginsdataservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PluginsDataService {

    private final AccountRepository accountRepository;

    public Optional<Account> findAccount(String code) {
        return accountRepository.findByCode(code);
    }

    public Optional<Device> findDevice(String accountCode, String deviceName) {
        return accountRepository.findDevice(accountCode, deviceName);
    }
}
