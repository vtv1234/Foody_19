package hcmute.edu.vn.foody_18.Model;

public class FoodLocation {
    private String foodLocationId;
    private String name;
    private int vote;
    private String address;
    private String thumbImage;
    private String coverImage;

    public FoodLocation() {
    }

    public FoodLocation(String foodLocationId, String name, int vote, String address, String thumbImage, String coverImage) {
        this.foodLocationId = foodLocationId;
        this.name = name;
        this.vote = vote;
        this.address = address;
        this.thumbImage = thumbImage;
        this.coverImage = coverImage;
    }

    public String getFoodLocationId() {
        return foodLocationId;
    }

    public void setFoodLocationId(String foodLocationId) {
        this.foodLocationId = foodLocationId;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getThumbImage() {
        return thumbImage;
    }

    public void setThumbImage(String thumbImage) {
        this.thumbImage = thumbImage;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FoodLocation{" +
                "foodLocationId='" + foodLocationId + '\'' +
                ", name='" + name + '\'' +
                ", vote=" + vote +
                ", address='" + address + '\'' +
                ", thumbImage='" + thumbImage + '\'' +
                ", coverImage='" + coverImage + '\'' +
                '}';
    }
}
