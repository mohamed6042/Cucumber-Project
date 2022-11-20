
  Feature: Buy different products from Vodafone website
    Scenario: Add three products to cart by select them directly and search bar
      Given user on main page of Vodafone shop
      When choose the products and procedd to checkout
      And enter all necessary info
      |Cairo|Hadayek El Kobba|Badr|113|8|9|mohamed|mohamedmahmod10@gmail.com|01015340028|
      Then will can complete by press submit button