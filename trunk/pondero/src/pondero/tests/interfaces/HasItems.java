package pondero.tests.interfaces;

public interface HasItems {

    public void $clearitems();

    public void $insertitem(String value, int index);

    public String $item(int index);

    public int $itemcount();

    public String[] $items();

    public void $removeitem(int index);

    public void $settitem(String value, int index);

}