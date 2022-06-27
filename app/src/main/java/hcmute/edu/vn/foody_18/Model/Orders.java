package hcmute.edu.vn.foody_18.Model;

public class Orders {
    private int Id;
    private int RestaurantId;
    private String RestaurantName;

    public int getId() {
        return Id;
    }

    public Orders() {
    }

    public Orders(int id, int restaurantId, String restaurantName, String foodName, int foodId, String image, int price, int quantity, String dateOrder, int totalPrice) {
        Id = id;
        RestaurantId = restaurantId;
        RestaurantName = restaurantName;
        FoodName = foodName;
        FoodId = foodId;
        Image = image;
        Price = price;
        Quantity = quantity;
        DateOrder = dateOrder;
        TotalPrice = totalPrice;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getRestaurantId() {
        return RestaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        RestaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        RestaurantName = restaurantName;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public int getFoodId() {
        return FoodId;
    }

    public void setFoodId(int foodId) {
        FoodId = foodId;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getDateOrder() {
        return DateOrder;
    }

    public void setDateOrder(String dateOrder) {
        DateOrder = dateOrder;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }

    private String FoodName;
    private int FoodId;
    private String Image;
    private int Price;
    private int Quantity;
    private String DateOrder;
    private int TotalPrice;
}
