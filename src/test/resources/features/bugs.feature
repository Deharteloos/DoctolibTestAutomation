Feature: Other bugs
  
  @severity=minor
  @Bug_1477
  Scenario Outline: Display issue
    Given Go to the home page
    When Click on the "<letter>" in the section search for practitioners at the bottom of the page
    And Scroll to the "<practitioner>"
    Then We see characters that are replaced by question marks in the address
    
    Examples:
      |letter|practitioner   |
      | B    | Thoraya Babba |

  
  @Recommendation_1565
  Scenario Outline: Absence of the French language in the language filters
    Given Go to the home page
    When Click on the link "Diététicien"
    And Unroll the filter on spoken languages
    Then The language "<language>" is not present
    
    Examples:
      |language  |
      | Français |