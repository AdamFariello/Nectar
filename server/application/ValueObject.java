import java.util.Arrays;

public abstract class ValueObject {

    protected abstract String[] getEqualityComponents();

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }

        if (!(o instanceof ValueObject)) {
            return false;
        }

        ValueObject v = (ValueObject) o;

        String[] arr1 = this.getEqualityComponents();
        String[] arr2 = v.getEqualityComponents();

        return Arrays.equals(arr1, arr2);
    }

    @Override
    public int hashCode(){
        int hash = 1;
        String[] components = getEqualityComponents();
        for (String s: components){
            hash *= s.hashCode();
        }
        return hash;
    }
}