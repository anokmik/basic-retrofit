package sample.retrofit.client.models;

import java.lang.reflect.Type;

public final class Response {
    private final String content;
    private final Type type;
    private final String mimeType;

    public Response(String content, Type type, String mimeType) {
        this.content = content;
        this.type = type;
        this.mimeType = mimeType;
    }

    public String getContent() {
        return content;
    }

    public Type getType() {
        return type;
    }

    public String getMimeType() {
        return mimeType;
    }

}