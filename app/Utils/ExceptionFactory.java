package Utils;

import play.Logger;

/**
 * Created by Meaks on 12/7/2015.
 */
public class ExceptionFactory {

    public static AppException getNewAppException(Exception exception) {
        Logger.error(exception.getMessage());
        AppException app = new AppException();
        app.initCause(exception);
        return app;
    }
}
