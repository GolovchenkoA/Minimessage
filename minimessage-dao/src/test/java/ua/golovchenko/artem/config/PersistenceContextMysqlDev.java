package ua.golovchenko.artem.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by головченко on 29.03.2017.
 */

@Configuration
@EnableTransactionManagement
//@ImportResource("classpath:persistence-context.xml")
@PropertySource(value={"classpath:jdbc-dev.properties"})
@ComponentScan("ua.golovchenko.artem.minimessage.dao")
public class PersistenceContextMysqlDev {

    @Value("${jdbcdev.driver}")
    String jdbcDriver;

    @Value("${jdbcdev.url}")
    String jdbcUrl;

     @Value("${jdbcdev.username}")
    String jdbcUsername;

    @Value("${jdbcdev.password}")
    String jdbcPassword;


    @Bean
    public DataSource getDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(jdbcDriver);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUsername);
        dataSource.setPassword(jdbcPassword);
        return dataSource;
    }

    @Bean
    public SessionFactory getSessionFactory() throws Exception {

        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "create");

        AnnotationSessionFactoryBean factory = new AnnotationSessionFactoryBean();
        factory.setPackagesToScan(new String [] {"ua.golovchenko.artem.minimessage.model"});
        factory.setDataSource(this.getDataSource());
        factory.setHibernateProperties(properties);

        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean(name="txManagerDev")
    public HibernateTransactionManager txManagerDev() throws Exception {
        HibernateTransactionManager tx = new HibernateTransactionManager();
        tx.setSessionFactory(getSessionFactory());
        //txName.setDataSource(...);
        return tx;
    }

}
