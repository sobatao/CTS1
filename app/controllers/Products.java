package controllers;

import com.avaje.ebean.Model;
import models.Product;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import views.html.products.details;
import views.html.products.list;

import java.util.List;


/**
 * Created by Meaks on 12/5/2015.
 */

@With(CatchAction.class)
public class Products extends Controller {

    private static final Form<Product> productForm = Form.form(Product.class);

    public  Result index() {
        return redirect(routes.Products.list(1));
    }

    public Result list( Integer page ) {

        List<Product> product = new Model.Finder<String, Product>(String.class , Product.class).all();

        return ok(list.render(product));

    }

    public Result show(String ean) {

        Product product =  Product.find.byId(ean) ;

        return TODO; //ok(detail.render(p));

    }

    public Result newProduct() {

        return ok(details.render(productForm));

    }


    /* implement remember last page number*/
    public Result details(String ean) {

        final Product product = Product.find.byId(ean);

        if (product == null) {

            flash("error",
                    String.format("Product %s does not exist.", ean)
            );

            //return notFound(String.format("Product %s does not exist.", ean));
            return redirect(routes.Products.list(1));
        }

        Form<Product> filledForm = productForm.fill(product);

        return ok(details.render(filledForm));
    }

    public Result save() {

        Form<Product> boundForm = productForm.bindFromRequest();

        if(boundForm.hasErrors()) {
            flash("error",
                    "Please correct the form below.");
            return badRequest(details.render(boundForm));
        }

        Product product = boundForm.get();



        final Product p = Product.find.byId(product.ean);
        String result = "";
        if ( p == null) {
            product.save();

        } else {
            product.update();
            result = "Successfully updated product %s";
        }

        flash("success",
                String.format( result, product)
        );

        return redirect(routes.Products.list(1));

    }


    public Result delete(String ean) {

        final Product product = Product.find.byId(ean);

        if(product == null) {

            flash("error",
                    String.format("Product %s does not exist.", ean)
            );

            //return notFound(String.format("Product %s does not exists.", ean));

        } else {

            product.delete();
            flash("success",
                    String.format("Successfully updated product %s", product)
            );

        }


        return redirect(routes.Products.list(1));
    }

    public Result picture(String ean) {
        final Product product = Product.find.byId(ean);
        if(product == null) return notFound();
        return redirect(routes.Products.list(1));
    }

}

