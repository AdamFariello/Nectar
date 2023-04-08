package server.application;

class UserTrackerSettingsVO extends ValueObject{
    public boolean automaticPurchase;

    @Override
    protected String[] getEqualityComponents() {
        String[] s = {Boolean.toString(automaticPurchase)};
        return s;
    }

}