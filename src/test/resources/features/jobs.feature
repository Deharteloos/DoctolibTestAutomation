# language: en
Feature: Doctolib jobs page bugs

  Background:
    Given Go to the home page
    When Click on about us in the footer
    And Click on the link "Rejoignez notre équipe"
    And Click on the link "Trouvez votre prochain emploi"
    And Language is set to french
  
  
  @severity=critical
  @Bug_1538 
  Scenario Outline: Problem on the filter
    When Apply "<country>" on the country filter
    And Apply "<city>" on the city filter
    Then No results should be shown
    
    Examples:
      |country |city       |
      | France | Berlin HQ |


  @severity=critical
  @Bug_1553 
  Scenario Outline: Redirection to a 404 error page 
    When Scroll to the footer
    And Click on the link "<link>"
    Then An 404 error page should not be returned
    
    Examples:
      |link                  |
      | Mission & Approche   |
      | Produits             |
      | Engagements          |
      | Technologie          |
      | L'équipe Doctolib    |
      | Culture d'entreprise |
      | Investisseurs        |
  

  @severity=minor
  @Bug_1536 
  Scenario Outline: Lining of the same filter choice on a website page
    When Unroll the filter on the cities
    Then The city "<city>" is not duplicated
    
    Examples:
      |city       |
      | Berlin HQ |