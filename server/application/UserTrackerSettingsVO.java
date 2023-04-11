package server.application;

class UserTrackerSettingsVO extends ValueObject{
    public boolean automaticPurchase;
    public boolean price;
    public boolean availability;
    

    @Override
    protected String[] getEqualityComponents() {
        String[] s = {Boolean.toString(automaticPurchase)};
        return s;
    }

}