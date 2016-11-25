package cz.muni.fi.pa165.deliveryservice.service.config;

import cz.muni.fi.pa165.deliveryservice.service.facade.ProductFacadeImpl;
import cz.muni.fi.pa165.deliveryservice.service.impl.ProductServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import cz.muni.fi.pa165.deliveryservice.PersistenceApplicationContext;
import cz.muni.fi.pa165.deliveryservice.service.facade.ShipmentFacadeImpl;
import cz.muni.fi.pa165.deliveryservice.service.impl.ShipmentServiceImpl;

/**
 * @author Viktor Bako
 */
@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackageClasses={ShipmentServiceImpl.class, ShipmentFacadeImpl.class, ProductFacadeImpl.class, ProductServiceImpl.class})
public class ServiceConfiguration {

	@Bean
	public Mapper dozer(){
		DozerBeanMapper dozer = new DozerBeanMapper();	
		return dozer;
	}
	
}
