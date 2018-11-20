package Business.Product;

/**
 *
 * @author neild
 */
public class ProductTransit implements ProductState {

    @Override
    public void nextState(Product p) {
        p.setState(new ProductStored());
    }

    @Override
    public void previousState(Product p) {
        p.setState(new ProductPending());
    }

    @Override
    public String getProductStatus(Product p) {
        return p.getName() + " is currently in transit to a warehouse.";
    }
}
