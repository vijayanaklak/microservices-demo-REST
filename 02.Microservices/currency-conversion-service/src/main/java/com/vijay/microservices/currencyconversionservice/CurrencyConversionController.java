package com.vijay.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeServiceProxy exchangeServiceProxy;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		Map<String, String> uriValues = new HashMap<String, String>();
		uriValues.put("from", from);
		uriValues.put("to", to);

		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8001/currency-exhange/from/{from}/to/{to}", CurrencyConversionBean.class, uriValues);

		CurrencyConversionBean response = responseEntity.getBody();

		return new CurrencyConversionBean(response.getId(), from, to, quantity, response.getConversionMultiple(),
				quantity.multiply(response.getConversionMultiple()), response.getPort());
	}

	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversionBean response = exchangeServiceProxy.retrieveExchangeValue(from, to);
		response.setTotalCalculatedAmount(quantity.multiply(response.getConversionMultiple()));
		response.setQuantity(quantity);

		logger.info("{}", response);
		// return new CurrencyConversionBean(response.getId(), from, to, quantity,
		// response.getConversionMultiple(),
		// quantity.multiply(response.getConversionMultiple()), response.getPort());

		return response;
	}

}
