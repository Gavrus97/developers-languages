package de.telran.developerslanguages;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Runner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
    }

    public static void main(String[] args) {
        String s = "postgres://kysecrnwmtcrwe:5aae8b63216f560667d821f734a3fe175a2f588c388ae6a063d10bf79d47b977@ec2-54-228-125-183.eu-west-1.compute.amazonaws.com:5432/df6743p5prjfoc";

        System.out.println(parse(s));
    }

    public static Map<String, String> parse(String s) {
        Map<String, String> res = new HashMap<>();

        var string = s.substring(11);
        var array = string.split("[@/:]");

        var url = String.format("jdbc:postgresql://%s:%s/%s", array[2], array[3], array[4]);
        var username = array[0];
        var password = array[1];

        res.put("url", url);
        res.put("username", username);
        res.put("password", password);

        return res;
    }
}
