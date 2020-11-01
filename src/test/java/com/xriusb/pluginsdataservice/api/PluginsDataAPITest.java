package com.xriusb.pluginsdataservice.api;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.xriusb.pluginsdataservice.model.Account;
import com.xriusb.pluginsdataservice.model.Device;
import com.xriusb.pluginsdataservice.model.HostResponse;
import com.xriusb.pluginsdataservice.service.PluginsDataService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PluginsDataAPITest {

    public static final String ACCOUNT_CODE = "clienteA";
    public static final String DEVICE_NAME = "XBox";
    public static final String PLUGIN_VERSION = "3.3.1";
    public static final String GET_DATA_URL = "/getData?accountCode=" + ACCOUNT_CODE + "&targetDevice="
            + DEVICE_NAME + "&pluginVersion=" + PLUGIN_VERSION;
    public static final String HOST_NAME = "clusterA";
    public static final Integer PING_TIME = 5;

    @Mock
    private PluginsDataService pluginsDataService;

    private MockMvc mockMvc;

    private PluginsDataAPI testee;


    @Before
    public void setup() {
        testee = new PluginsDataAPI(pluginsDataService);

        mockMvc = MockMvcBuilders.standaloneSetup(testee).build();
    }

    @Test
    public void getPluginsData() throws Exception {
        when(pluginsDataService.findAccount(ACCOUNT_CODE)).thenReturn(Optional.of(
                Account.builder().code(ACCOUNT_CODE).build()));

        Optional<Device> testDevice = Optional.of(Device.builder().name(DEVICE_NAME).pingTime(5).pluginVersion(PLUGIN_VERSION).build());

        when(pluginsDataService.findDevice(ACCOUNT_CODE, DEVICE_NAME)).thenReturn(testDevice);
        when(pluginsDataService.getHostResponse(testDevice.get())).thenReturn(HostResponse.builder()
                .hostName(HOST_NAME).viewCode("testViewCode").pingTime(PING_TIME).build());

        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(GET_DATA_URL))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Mockito.verify(pluginsDataService).findAccount(ACCOUNT_CODE);
        Mockito.verify(pluginsDataService).findDevice(ACCOUNT_CODE, DEVICE_NAME);

        HostResponse response = new XmlMapper().readValue(result.getResponse().getContentAsString(), HostResponse.class);
        assertEquals(HOST_NAME, response.getHostName());
        assertEquals(PING_TIME, response.getPingTime());
    }
}