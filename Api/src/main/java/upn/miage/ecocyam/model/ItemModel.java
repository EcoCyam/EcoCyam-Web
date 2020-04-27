package upn.miage.ecocyam.model;

public class ItemModel {
    private String name;
    private Double overallScore;
    private String barcode;
    private byte[] image;
    private int categoryId;

    public ItemModel(String name, Double overallScore, String barcode, byte[] image, int categoryId) {
        this.name = name;
        this.overallScore = overallScore;
        this.barcode = barcode;
        this.image = image;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getOverallScore() {
        return overallScore;
    }

    public void setOverallScore(Double overallScore) {
        this.overallScore = overallScore;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
