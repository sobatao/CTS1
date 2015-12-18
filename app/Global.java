import Utils.AnnotationDateFormatter;
import play.Application;
import play.GlobalSettings;
import play.data.format.Formatters;
import play.data.format.Formatters.SimpleFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Meaks on 12/6/2015.
 */
public class Global extends GlobalSettings {

    public void onStart(Application app) {
        // Register our DateFormater
        Formatters.register(Date.class,
                new SimpleFormatter<Date>() {

                    private final static String PATTERN = "dd-MM-yyyy";

                    public Date parse(String text, Locale locale)
                            throws java.text.ParseException {
                        if(text == null || text.trim().isEmpty()) {
                            return null;
                        }
                        SimpleDateFormat sdf =
                                new SimpleDateFormat(PATTERN, locale);
                        sdf.setLenient(false);
                        return sdf.parse(text);
                    }

                    public String print(Date value, Locale locale){
                        if(value == null) {
                            return "";
                        }
                        return new SimpleDateFormat(PATTERN, locale)
                                .format(value);
                    }

                });
        Formatters.register(Date.class, new AnnotationDateFormatter());

    }



    }


