package ru.pihta.nocturnaltransport.config;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import ru.pihta.nocturnaltransport.Application;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DBConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    DataSource dataSource() {
        final PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setUrl("jdbc:postgresql://localhost:5432/transport-nocturnal");
        ds.setUser("pihta");
        ds.setPassword("123");
        dataSource = ds;
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("ru.pihta.nocturnaltransport.model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    Properties hibernateProperties() {
        return new Properties() {

            private static final long serialVersionUID = -745721271420087477L;

            {
                setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            }
        };
    }
}
