package upn.miage.ecocyam.model;

public class SearchModel {
    private String barcode;
    private String keyword;

    public SearchModel(){}

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
