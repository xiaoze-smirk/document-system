package edu.fjnu.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by xiaozemaliya on 2017/4/2.
 */
@ComponentScan(basePackages = "edu.fjnu") //这里指定你要扫描的包及其子包子类
@SpringBootApplication
@MapperScan("edu.fjnu.mapper")//扫描：该包下相应的class,主要是MyBatis的持久化类.
@EnableTransactionManagement
public class start {
	public static void main(String[] args) {
		SpringApplication.run(start.class, args);
	}
}
