/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.hackerrank;

import static com.abc.utils.RegexConstant.REGEX_CSV_FULL;
import com.spring5.MyApplication;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @author javaugi
 */
public class FirstShortestLongestCityNames {
    private static final Logger log = LoggerFactory.getLogger(FirstShortestLongestCityNames.class);

    public static void main(String[] args) {
        //testDataPopulation();        
        doQueryFromFileData(args);
    }

    private static void testDataPopulation() {
        String[] strArr = {"Alaska,	AK,	Juneau,	Anchorage\n", "Arizona,	AZ,	Phoenix,	Phoenix"};
        for (String line: strArr) {
            String[] tokens = line.split(REGEX_CSV_FULL);
            System.out.println("line=" + line + "\n" + Arrays.toString(tokens));
            for (String str: tokens) {
                System.out.println("1 token=" + str);
                str = str.replaceAll(REGEX_CSV_FULL, "");
                System.out.println("2 token=" + str);
            }
        }
    }

    private static void doQueryFromFileData(String[] args) throws BeansException {
        // Start the Spring application and get the application context
        ConfigurableApplicationContext context = SpringApplication.run(MyApplication.class, args);
        log.info("FirstShortestLongestCityNames started with context {}", context);

        // Application logic can be placed here, after the context is initialized
        StationNativeQueryService myService = context.getBean(StationNativeQueryService.class);
        log.info("StationNativeQueryService {}", myService);
        List<String> results = myService.doQuery(getQuery());
        log.info("StationNativeQueryService results {}", results);
        results.stream().forEach(System.out::println);

        // Close the application context when done
        context.close();
    }

    private static String getQuery() {
        String q = "(select city, length(city) as city_len  from station order by length(city) asc, city asc limit 1) "
                + "union all "
                + "(select city, length(city) as city_len from station order by length(city) desc, city asc "
                + "limit 1)";

        return q;
    }    
    
    //Q1 is for Oracle or Standard
    private static final String Q1 = "select distinct city from station where lower(SUBSTR(city, 1, 1)) in ('a', 'e', 'i', 'o', 'u')";
    private static final String Q2 = "SELECT DISTINCT CITY FROM STATION WHERE REGEXP_LIKE(CITY, '^[aeiouAEIOU]', 'i');";
}
