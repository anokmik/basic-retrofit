package sample.retrofit.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit.*;
import sample.retrofit.server.MockServer;

public final class Communicator {

    public Communicator() {

    }

    public RestAdapter getRestAdapter() {
        return new RestAdapter.Builder()
                .setEndpoint(Endpoints.newFixedEndpoint(Config.BASE_URL))
                .setClient(new MockServer())
                .setConverter(new SimpleConverter(getGson()))
                .setErrorHandler(errorHandler)
                .setRequestInterceptor(interceptor)
                .setProfiler(profiler)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }

    public Gson getGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    private interface Config {
        String BASE_URL = "https://sample.retrofit.com/api";
    }

    private final ErrorHandler errorHandler = new ErrorHandler() {

        @Override
        public Throwable handleError(RetrofitError cause) {
            System.out.println("handle error: " + cause.getMessage());
            return cause;
        }

    };

    private final RequestInterceptor interceptor = new RequestInterceptor() {

        @Override
        public void intercept(RequestFacade request) {
            //Append additional data to request: headers, query or path params,
        }

    };

    private final Profiler profiler = new Profiler() {

        @Override
        public Object beforeCall() {
            return null;
        }

        @Override
        public void afterCall(RequestInformation requestInfo, long elapsedTime, int statusCode, Object beforeCallData) {

        }

    };

}