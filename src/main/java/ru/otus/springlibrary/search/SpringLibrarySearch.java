package ru.otus.springlibrary.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class SpringLibrarySearch {

    public static void main(String[] args) {
        SpringApplication.run(SpringLibrarySearch.class);
    }
}


