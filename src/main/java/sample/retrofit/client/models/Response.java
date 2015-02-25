package sample.retrofit.client.models;

public final class Response {
    private final String content;
    private final String mimeType;

    public Response(String content, String mimeType) {
        this.content = content;
        this.mimeType = mimeType;
    }

    public String getContent() {
        return content;
    }

    public String getMimeType() {
        return mimeType;
    }

    @Override
    public String toString() {
        return "Response{" +
                "content='" + content + '\'' +
                ", mimeType='" + mimeType + '\'' +
                '}';
    }

}