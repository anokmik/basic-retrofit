package sample.retrofit;

import retrofit.Callback;
import retrofit.RetrofitError;
import sample.retrofit.client.ApiSpecs;
import sample.retrofit.client.Communicator;
import sample.retrofit.client.ServerTypedOutput;
import sample.retrofit.client.models.RequestUser;
import sample.retrofit.client.models.Response;
import sample.retrofit.client.models.ResponseUser;

public final class Main {

    public static void main(String[] args) {
        System.out.println("Let's get started!");
        Communicator communicator = new Communicator();
        ApiSpecs specs = communicator.getRestAdapter().create(ApiSpecs.class);

        ResponseUser responseUser;
        RequestUser requestUser;
        Response response;

        responseUser = specs.getUserPath(Config.USER_ID);
        printUser(responseUser);

        responseUser = specs.getUserQuery(Config.USER_ID);
        printUser(responseUser);

        responseUser = specs.getUserField(Config.USER_ID);
        printUser(responseUser);

        requestUser = new RequestUser(Config.USER_ID);
        printUser(responseUser);

        responseUser = specs.getUserBody(requestUser);
        printUser(responseUser);

        responseUser = specs.getUserTypedOutput(new ServerTypedOutput<RequestUser>(requestUser));
        printUser(responseUser);

        response = specs.getUserCustomResponse(Config.USER_ID);
        printCustomResponse(response);
        printNewLine();

        try {
            specs.getUserError(Config.USER_ID);
        } catch (RetrofitError e) {
            System.out.println("error: url " + e.getUrl() + ", cause " + e.getCause());
        } finally {
            printNewLine();
        }

        specs.getUserCallback(Config.USER_ID, new Callback<ResponseUser>() {

            @Override
            public void success(ResponseUser user, retrofit.client.Response response) {
                System.out.println("received: " + user);
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("error: " + error.getMessage());
            }

        });
    }

    private static void printCustomResponse(Response response) {
        System.out.println("received: " + response.getContent() + ", mime " + response.getMimeType() + ", type " + response.getType());
    }

    private static void printUser(ResponseUser user) {
        System.out.println("received: " + user);
        System.out.println();
    }

    private static void printNewLine() {
        System.out.println();
    }

    public interface Config {
        long USER_ID = 777;
    }

}