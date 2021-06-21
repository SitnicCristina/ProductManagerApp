package md.tekwill.app;

import md.tekwill.exceptions.ProductUpdateUnknownPropertyException;

public interface ProductManagement {
   void run() throws ProductUpdateUnknownPropertyException;
}
