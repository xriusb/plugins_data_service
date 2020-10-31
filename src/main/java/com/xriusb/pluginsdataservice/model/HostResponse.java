package com.xriusb.pluginsdataservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JacksonXmlRootElement(localName = "q")
public class HostResponse {
    @JsonProperty("h")
    private String hostName;
    @JsonProperty("pt")
    private Integer pingTime;
    @JsonProperty("c")
    private String viewCode;
}
