package sample.retrofit.client;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;
import sample.retrofit.client.models.Response;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

final class SimpleConverter implements Converter {
    private final Gson gson;
    private final Charset charset;

    public SimpleConverter(Gson gson) {
        this(gson, Charset.forName("UTF-8"));
    }

    public SimpleConverter(Gson gson, Charset charset) {
        if (gson == null) throw new NullPointerException("gson == null");
        if (charset == null) throw new NullPointerException("charset == null");
        this.gson = gson;
        this.charset = charset;
    }

    @Override
    public Object fromBody(TypedInput body, Type type) throws ConversionException {
        InputStream inputStream = null;
        try {
            inputStream = body.in();
            String bodyContent = IOUtils.toString(inputStream, charset.displayName());
            if (Response.class.equals(type)) {
                return new Response(bodyContent, body.mimeType());
            } else {
                return gson.fromJson(bodyContent, type);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return null;
    }

    @Override
    public TypedOutput toBody(Object object) {
        return new JsonTypedOutput(object, gson, charset);
    }

}