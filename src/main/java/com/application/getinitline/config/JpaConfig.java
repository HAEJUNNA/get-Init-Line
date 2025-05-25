package com.application.getinitline.config;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

 //  @Bean
//  public DataSource dataSource() {
//
// //  EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder(); // EmbeddedDatabaseBuilder도움을 받아 설정
// //  DataSourceBuilder builder = DataSourceBuilder.create() // 직접 JDBC 방식으로 설정
// //      .driverClassName()
// //      .type().url()
// //      .username()
// //      .password()
// //      .build();
//   return builder.setType(EmbeddedDatabaseType.H2).build();
//
//  }
// Properties에서 설정에서 자바코드로 설정하는 방법
 @ConfigurationProperties("spring.datasource")
 @Bean
 public DataSource dataSource() {
  return DataSourceBuilder.create().build(); // Config.properties 파일의 설정을 읽어들인다.
 }

 @Bean
 public DataSource dataSource2() {
  return DataSourceBuilder.create().build(); // Config.properties 파일의 설정을 읽어들인다.
 }

 @Bean
 public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {

  HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
  vendorAdapter.setGenerateDdl(true);

  LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
  factory.setJpaVendorAdapter(vendorAdapter);
  factory.setPackagesToScan("com.application.getinitline");
//   factory.setDataSource(dataSource);
  factory.setDataSource(dataSource());
  return factory;
 }

 @Bean
 public LocalContainerEntityManagerFactoryBean entityManagerFactory2(DataSource dataSource) {

  HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
  vendorAdapter.setGenerateDdl(true);

  LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
  factory.setJpaVendorAdapter(vendorAdapter);
  factory.setPackagesToScan("com.application.getinitline");
//   factory.setDataSource(dataSource);
  factory.setDataSource(dataSource());
  return factory;
 }

 @Bean
 public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

  JpaTransactionManager txManager = new JpaTransactionManager();
  txManager.setEntityManagerFactory(entityManagerFactory);
  return txManager;
 }

 @Bean
 public PlatformTransactionManager transactionManager2(EntityManagerFactory entityManagerFactory) {

  JpaTransactionManager txManager = new JpaTransactionManager();
  txManager.setEntityManagerFactory(entityManagerFactory);
  return txManager;
 }

// @Bean
// public ChaninedTransactionManager transactionManager(PlatformTransactionManager transactionManager1, PlatformTransactionManager transactionManager2) {}
}
