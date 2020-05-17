package upn.miage.ecocyam.model;

public class ItemModel {
    private String name;
    private Double overallScore;
    private String barcode;
    private byte[] image;
    private int categoryId;
    private double note1;
    private double note2;
    private double note3;

    public ItemModel(String name, Double overallScore, String barcode, byte[] image, int categoryId) {
        this.name = name;
        this.overallScore = overallScore;
        this.barcode = barcode;
        this.image = image;
        this.categoryId = categoryId;
        this.note1 = note1;
        this.note2 = note2;
        this.note3 = note3;
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

    public double getNote1() {
        return note1;
    }

    public void setNote1(double note1) {
        this.note1 = note1;
    }

    public double getNote2() {
        return note2;
    }

    public void setNote2(double note2) {
        this.note2 = note2;
    }

    public double getNote3() {
        return note3;
    }

    public void setNote3(double note3) {
        this.note3 = note3;
    }
}
