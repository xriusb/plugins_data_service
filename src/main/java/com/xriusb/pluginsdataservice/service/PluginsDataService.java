package com.xriusb.pluginsdataservice.service;

import com.xriusb.pluginsdataservice.model.Account;
import com.xriusb.pluginsdataservice.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PluginsDataService {

    private AccountRepository accountRepository;

    public Optional<Account> findByCode(String code) {
        return accountRepository.findByCode(code);
    }



}
