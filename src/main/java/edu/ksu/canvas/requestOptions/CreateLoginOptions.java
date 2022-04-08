package edu.ksu.canvas.requestOptions;

public class CreateLoginOptions extends BaseOptions {

    public CreateLoginOptions userId(String userId) {
        addSingleItem("user[id]", userId);
        return this;
    }

    public CreateLoginOptions password(String password) {
        addSingleItem("login[password]", password);
        return this;
    }

}
