package com.alcatel.axs.webc.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.dialect.MySQL5Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.alcatel.axs.webc.persistence.repository")
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class PersistenceConfig {

	@Autowired
	private Environment env;

	@Bean
	public File datastoreBaseDirectory(
			final @Value("datastore") String datastoreBaseDirectoryPath) {
		final File rv = new File(datastoreBaseDirectoryPath);
		if (!(rv.isDirectory() || rv.mkdirs())) {
			throw new RuntimeException(
					String.format(
							"Could not initialize '%s' as base directory for datastore!",
							rv.getAbsolutePath()));
		}

		return rv;
	}

	@Bean
	public DataSource restDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
//		System.out.println("env.getProperty(\"jdbc.driverClassName\") = "+env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.pass"));

		return dataSource;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter(final Environment environment) {
		final HibernateJpaVendorAdapter rv = new HibernateJpaVendorAdapter();

		rv.setDatabase(Database.MYSQL);
		rv.setDatabasePlatform(MySQL5Dialect.class.getName());
		boolean acceptsProfilesDev = environment.acceptsProfiles("dev");
		System.out.println("acceptsProfilesDev = "+acceptsProfilesDev);
		rv.setGenerateDdl(true);
		boolean acceptsProfilesDevTest = environment.acceptsProfiles("dev", "test");
		System.out.println("acceptsProfilesDevTest = " + acceptsProfilesDevTest);
		rv.setShowSql(true);

		return rv;
	}

	@Bean
	public PlatformTransactionManager transactionManager(
			final EntityManagerFactory entityManagerFactory) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			final Environment environment, final DataSource dataSource,
			final JpaVendorAdapter jpaVendorAdapter) {
		final Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.generate_statistics", "false");
		if (environment.acceptsProfiles("dev")) {
			properties.put("hibernate.hbm2ddl.auto", "create-drop");
		}

		final LocalContainerEntityManagerFactoryBean rv = new LocalContainerEntityManagerFactoryBean();
		rv.setPersistenceUnitName("com.alcatel.axs.webc.persistence.entities_resourceLocale");
		rv.setPackagesToScan("com.alcatel.axs.webc.persistence.entities");
		rv.setJpaDialect(new HibernateJpaDialect());
		rv.setJpaVendorAdapter(jpaVendorAdapter);
		rv.setDataSource(dataSource);
		rv.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		rv.setJpaPropertyMap(properties);
		return rv;
	}
}
