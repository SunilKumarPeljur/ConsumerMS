/**
 * 
 */
package com.training;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author admin
 *
 */

@RestController
public class ConsumerService {

	@Autowired
	private DiscoveryClient client;
	
	@RequestMapping("/order")
	public List<Orders> getData() {
		
		List<ServiceInstance> list = client.getInstances("orders-ms");
		
		if(list != null && !list.isEmpty()) {
			URI uri = list.get(0).getUri();
			if(uri != null) {
				return (new RestTemplate()).getForObject(uri, List.class);	 
			}
		}
		return null;
	}
}
