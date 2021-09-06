Feature: Other bugs
  
  @Recommendation_1565
  Scenario Outline: Absence of the French language in the language filters
    Given Go to the home page
    When Click on the link "Diététicien"
    And Unroll the filter on spoken languages
    Then The language "<language>" is not present
    
    Examples:
      |language  |
      | Français |