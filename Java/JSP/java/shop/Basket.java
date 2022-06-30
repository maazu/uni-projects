package shop;


import java.util.*;

public class Basket {


    Map<Product, Integer> items;
    ShopDB db;

    public static void main(String[] args) {
        Basket b = new Basket();
        b.addItem("art1");
        System.out.println( b.getTotalString() );
        b.clearBasket();
        System.out.println( b.getTotalString() );
        // check that adding a null String causes no problems
        String pid = null;
        b.addItem( pid );
        System.out.println( b.getTotalString() );
    }

    public Basket() {
        db = ShopDB.getSingleton();
        items = new HashMap<>();
    }

    /**
     *
     * @return Collection of Product items that are stored in the basket
     *
     * Each item is a product object - need to be clear about that...
     *
     * When we come to list the Basket contents, it will be much more
     * convenient to have all the product details as items in this way
     * in order to calculate that product totals etc.
     *
     */
    public Map<Product, Integer> getItems() {
        return items;
    }

    /**
     * empty the basket - the basket should contain no items after calling this method
     */
    public void clearBasket() {
        items = new HashMap<>();
    }

    /**
     *
     *  Adds an item specified by its product code to the shopping basket
     *
     * @param pid - the product code
     */
    public void addItem(String pid) {
        // need to look the product name up in the
        // database to allow this kind of item adding...
        addItem( getItem(pid) );


    }

    public void addItem(Product p) {
        if (p != null) {
            if (items.containsKey(p)) {
                items.put(p, items.get(p) + 1);
            } else {
                items.put(p, 1);
            }
        }
    }

    public Product getItem(String pid) {
        Product p = null;
        System.out.println(pid);
        if (pid != null) {
            for (Product prod : items.keySet()) {
                if (pid.equals(prod.PID)) {
                    p = prod;
                }
            }
        }
        if (p == null) {
            p = db.getProduct( pid );
        }
        return p;
    }

    public void deleteItem(String pid) {
        Product p = getItem(pid);
        if (p != null) {
            items.remove(p);
        }
    }


    public void editBasket(String pid, Integer quantity) {
        Product p = getItem(pid);
        if (p != null && items.containsKey(p)) {
            int currentValue = items.get(p);
            currentValue = currentValue + quantity;
            if (currentValue <= 0) {
                items.remove(p);
            } else {
                items.put(p, currentValue);
            }
        }

    }

    /**
     *
     * @return the total value of items in the basket in pence
     */
    public int getTotal() {
        // iterate over the set of products...
        int total = 0;
        for (Product p : items.keySet()) {
            total += p.price * items.get(p); // adding and multiplying quantity
            System.out.println(items.get(p));
        }
        return total;
    }

    /**
     *
     * @return the total value of items in the basket as
     * a pounds and pence String with two decimal places - hence
     * suitable for inclusion as a total in a web page
     */
    public String getTotalString() {
        return Product.formatPrice(getTotal());
    }
}
