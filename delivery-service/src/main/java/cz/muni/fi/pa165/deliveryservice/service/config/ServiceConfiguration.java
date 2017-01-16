package cz.muni.fi.pa165.deliveryservice.service.config;

import cz.muni.fi.pa165.deliveryservice.PersistenceApplicationContext;
import cz.muni.fi.pa165.deliveryservice.service.facade.CustomerFacadeImpl;
import cz.muni.fi.pa165.deliveryservice.service.facade.ProductFacadeImpl;
import cz.muni.fi.pa165.deliveryservice.service.facade.ShipmentFacadeImpl;
import cz.muni.fi.pa165.deliveryservice.service.facade.UserFacadeImpl;
import cz.muni.fi.pa165.deliveryservice.service.impl.CustomerServiceImpl;
import cz.muni.fi.pa165.deliveryservice.service.impl.ProductServiceImpl;
import cz.muni.fi.pa165.deliveryservice.service.impl.ShipmentServiceImpl;
import cz.muni.fi.pa165.deliveryservice.service.impl.UserServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Viktor Bako
 */
@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackageClasses={ShipmentServiceImpl.class, ShipmentFacadeImpl.class,
		ProductFacadeImpl.class, ProductServiceImpl.class, UserFacadeImpl.class, UserServiceImpl.class,
		CustomerServiceImpl.class, CustomerFacadeImpl.class})
public class ServiceConfiguration {

	@Bean
	public Mapper dozer(){
		DozerBeanMapper dozer = new DozerBeanMapper();	
		return dozer;
	}
	
}
