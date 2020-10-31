package com.xriusb.pluginsdataservice.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xriusb.pluginsdataservice.model.Account;
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
    }

    public Optional<Account> findByCode(String code) {
        return accounts.stream().filter(account -> code.equals(account.getCode())).findFirst();
    }
}