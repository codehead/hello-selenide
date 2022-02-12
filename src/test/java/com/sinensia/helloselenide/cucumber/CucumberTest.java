package com.sinensia.helloselenide.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"json:build/reports/cucumber-report.json"} )
public class CucumberTest {
}
