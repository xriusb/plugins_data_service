package com.xriusb.pluginsdataservice.service.loadbalancer;

import com.xriusb.pluginsdataservice.model.Host;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class WeightedLoadBalancerTest {

    private LoadBalancer weightedLoadBalancer;

    @Test
    public void testWeights() {
        List<Host> hosts = Arrays.asList(
                Host.builder().name("clientA").weight(20).build(),
                Host.builder().name("clientB").weight(80).build());
        weightedLoadBalancer = new WeightedLoadBalancer(hosts);
        System.out.println(weightedLoadBalancer.getHost());
        System.out.println(weightedLoadBalancer.getHost());
        System.out.println(weightedLoadBalancer.getHost());
        System.out.println(weightedLoadBalancer.getHost());
    }


}