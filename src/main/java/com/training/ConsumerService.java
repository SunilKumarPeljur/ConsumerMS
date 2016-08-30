/**
 * 
 */
package com.training;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author admin
 *
 */

@RestController
@RequestMapping("/api")
public class ConsumerService {

	@Autowired
	private DiscoveryClient client;
	
	@RequestMapping("/order")
	public List<Orders> getData() {
		
		List<ServiceInstance> list = client.getInstances("orders-ms");
		/*Map<String,Object> map = new HashMap<String, Object>();
		map.put("id", id);*/
		if(list != null && !list.isEmpty()) {
			URI uri = list.get(0).getUri();
			if(uri != null) {
				return (new RestTemplate()).getForObject(uri.toString() + "/api/order", List.class); 
			}
		}
		return null;
	}
	
	@RequestMapping(value="/orderPost", method=RequestMethod.POST)
	public ResponseEntity<String> postOrders(@RequestBody Orders orders) {
		
		List<ServiceInstance> list = client.getInstances("orders-ms");
		
		if(list != null && !list.isEmpty()) {
			URI uri = list.get(0).getUri();
			if(uri != null) {
				return (new RestTemplate()).postForEntity(uri + "/api/order", orders, String.class);
			}
		}
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
}
