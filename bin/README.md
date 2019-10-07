# Coverage exercise

## Exercise description

The application that's in front of you is a backend part of a simple web shop that has whole its functionality exposed via REST endpoints. Your goal is to cover it with tests. 

Few remarks:
* You will find simple documentation below this section. It consists of available endpoints list and small functionality description. 
* It's totally up to you what kind of testing tools you want to use. _Cucumber_ and _RestAssured_ frameworks setup is merely as an example.
* The shop is a simple Spring Boot application with an in-memory database (H2). If you want to launch it yourself, simply run the `OnlineShop.main()` method. The whole project is managed by Maven - it may need to download few dependencies before compiling. 
* Unit tests are not a goal of this exercise, but if you feel like covering code with them - go for it!
* If you find any bugs please report them in any suitable way (ideally as a failing test with a Javadoc description). 
* Don't change the production code, but feel free to modify the test data: `/qa-coverage-excercise/src/main/resources/data.sql`


## Endpoints

### GET `/items`
Returns all the items in the store. Response with code `200` contains `Item[]`.
 
### PUT `/cart/items/{id}`
Puts an `Item` with given ID into user's cart.

Responses:
1. `200` - operation successful
1. `400` - malformed request
1. `404` - itemDTO not found

### DELETE `/cart/items/{id}`
Removes an itemDTO from a cart.

Responses:
1. `200` - itemDTO successfully removed from the cart
1. `400` - malformed request
1. `404` - itemDTO not found or itemDTO not in the cart

### GET `/cart`
View the card. Response with code `200` contains `Cart`.

### PUT `/cart/voucher/{id}`
Apply a voucher to the cart. Voucher id should be a 10-digits long number. Only one voucher can be applied.

### DELETE `/cart/voucher`
Remove a voucher from the cart.

### POST `/order`
Place an `Order` for a User's `Cart`.

## Structures

```
Cart {
    float total_price;
    Map<Item, byte> items;
    int discount;
    int voucherDiscount;
}
```

```
Item {
    int id;
    String name;
    float price;
}
```

```
Order {
    float total_price;
    float price_after_discount;
    int discount;
    int voucherDiscount;
    Date orderDate;
    String description;
    List<Item> items;
}
```


## Business flow

1. Show available items
1. Edit cart - add/remove a number of items
1. Add/remove a discount (10-digits number)
1. Calculate discount and apply vouchers
1. Place the Order and get the confirmation

#### Extra functionality

An additional discount is applied for given total price ranges:

|range          | discount  |
| ---           | ---       |
| 0 - 100       | 0%        |
| 101 - 500     | 5%        |
| 501 - 1000    | 10%       |

## SLAs

* No HTTP 500 error code allowed on the permanent basis, e.g. case when internal bug causes error code 500
to be returned is not desirable.
* Turn-around time for an arbitrary request must not exceed 1 second
