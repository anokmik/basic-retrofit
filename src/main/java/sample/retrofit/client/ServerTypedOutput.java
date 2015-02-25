package sample.retrofit.client;

import com.google.gson.Gson;
import retrofit.mime.TypedOutput;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

public final class ServerTypedOutput<T> implements TypedOutput {
    private final Gson converter;
    private final byte[] bodyBytes;
    private final String mimeType;

    public ServerTypedOutput(final T data) {
        this(data, "UTF-8");
    }

    public ServerTypedOutput(final T data, final String encoding) {
        this.converter = new Communicator().getGson();
        this.bodyBytes = buildBody(data, data.getClass(), encoding);
        this.mimeType = "application/x-www-form-urlencoded; charset=" + encoding;
    }

    public static <T> ServerTypedOutput<T> build(final T data) {
        return new ServerTypedOutput<T>(data);
    }

    @Override
    public String fileName() {
        return null;
    }

    @Override
    public String mimeType() {
        return mimeType;
    }

    @Override
    public long length() {
        return bodyExist() ? bodyBytes.length : 0;
    }

    @Override
    public void writeTo(OutputStream out) throws IOException {
        if (bodyExist()) {
            out.write(bodyBytes);
        }
    }

    private byte[] buildBody(final T requestBody, final Type type, final String encoding) {
        try {
            return ("d=" + converter.toJson(requestBody, type)).getBytes(encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean bodyExist() {
        return (bodyBytes != null);
    }

}