package sample.retrofit.server;

import com.google.gson.Gson;
import retrofit.RetrofitError;
import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedString;
import sample.retrofit.client.Communicator;
import sample.retrofit.client.models.ResponseUser;

import java.io.IOException;
import java.net.URI;

public final class MockServer implements Client {
    private final Gson converter;

    {
        converter = new Communicator().getGson();
    }

    @Override
    public Response execute(Request request) throws IOException {
        return new Response(request.getUrl(), 200, "", request.getHeaders(), buildResponse(request));
    }

    private TypedInput buildResponse(Request request) {
        URI uri = URI.create(request.getUrl());
        String path = uri.getPath();
        ResponseUser responseUser = new ResponseUser("Alex", "Varnov", 36, 161.124);
        if (path.contains("error")) {
            throw RetrofitError.unexpectedError(request.getUrl(), new IOException("you can't always get what you want..."));
        } else if (path.contains("input")) {
            return new TypedString("shIt happens");
        } else if (path.contains("user")) {
            return new TypedByteArray("application/json", converter.toJson(responseUser, responseUser.getClass()).getBytes());
        }
        return null;
    }

}