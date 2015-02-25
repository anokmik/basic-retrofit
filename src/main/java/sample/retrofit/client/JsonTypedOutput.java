package sample.retrofit.client;

import com.google.gson.Gson;
import retrofit.mime.TypedOutput;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

final class JsonTypedOutput implements TypedOutput {
    private final Charset charset;
    private final byte[] jsonBytes;

    public JsonTypedOutput(Object data, Gson gson, Charset charset) {
        this.charset = charset;
        this.jsonBytes = gson.toJson(data).getBytes(charset);
    }

    @Override
    public String fileName() {
        return null;
    }

    @Override
    public String mimeType() {
        return "application/json; charset=" + charset;
    }

    @Override
    public long length() {
        return jsonBytes.length;
    }

    @Override
    public void writeTo(OutputStream out) throws IOException {
        out.write(jsonBytes);
    }

}