package sample.retrofit.client.models;

import com.google.gson.annotations.Expose;

public final class RequestUser {
    @Expose
    private final long id;

    public RequestUser(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return RequestUser.class.getSimpleName() + "{" +
                "id=" + id +
                '}';
    }

}