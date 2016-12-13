package cz.muni.fi.pa165.deliveryservice;

import cz.muni.fi.pa165.deliveryservice.service.config.ServiceConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.IOException;


@Configuration
@Import(ServiceConfiguration.class)
//@ComponentScan(basePackageClasses = {SampleDataLoadingFacadeImpl.class})
public class SampleDataConfiguration {

    @Inject
    //SampleDataLoadingFacade sampleDataLoadingFacade;

    @PostConstruct
    public void dataLoading() throws IOException {
        //sampleDataLoadingFacade.loadData();
    }
}