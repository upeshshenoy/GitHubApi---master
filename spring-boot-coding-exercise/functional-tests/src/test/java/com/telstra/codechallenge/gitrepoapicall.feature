
Feature: As an api user I want to find the oldest user accounts with zero followers from Git Repo

  Scenario: Get give number of user accounts with zero followers from Git Repo
    Given url microserviceUrl
    And path '/getoldrepousers'
    When method GET
    Then status 200
    And match header Content-Type contains 'application/json'
    * def repoSchema = { repoId : '#string', html_url : '#string',login : "#string" }
    And match response == '#[30] repoSchema' 


